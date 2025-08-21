package com.filmyai.backend.service.Login;

import com.filmyai.backend.config.jwt.JwtTokenService;
import com.filmyai.backend.dto.Login.LoginRequestDto;
import com.filmyai.backend.dto.Login.LoginResponseDto;
import com.filmyai.backend.model.Users;
import com.filmyai.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class LoginService {


    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    private final UserRepository usersRepository;




    public LoginService(AuthenticationManager authenticationManager, JwtTokenService jwtTokenService, UserRepository usersRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
        this.usersRepository = usersRepository;
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto loginRequest) {

        // Check if user exists
//        Users user = (Users) usersRepository.findUsersByEmail(loginRequest.email());
//        if (user == null) {
//            throw new BadCredentialsException("Invalid email or password");
//        }

        Users user = usersRepository.findUsersByEmail(loginRequest.email())
                .orElseThrow(() -> new BadCredentialsException("Invalid email or password"));



        // Perform authentication
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(), loginRequest.password()
                )
        );

        // Generate JWT token
        String token = jwtTokenService.generateToken(auth);
        return new LoginResponseDto(token, "Login successful");
    }

}
