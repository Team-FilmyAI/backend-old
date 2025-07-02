package com.filmyai.backend.service.SignUp;

import com.filmyai.backend.dto.SignUp.SignUpRequestDto;
import com.filmyai.backend.dto.SignUp.SignUpResponseDto;
import com.filmyai.backend.enums.Role;
import com.filmyai.backend.model.Users;
import com.filmyai.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignUpService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public SignUpResponseDto registerUser(SignUpRequestDto requestDto) {
        if (userRepository.findUsersByEmail(requestDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use");
        }

        Users newUser = Users.builder()
                .firstName(requestDto.getFirstName())
                .lastName(requestDto.getLastName())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .enabled(true)
                .role(Role.valueOf(requestDto.getRole()))
                .build();

        userRepository.save(newUser);
        return new SignUpResponseDto("User registered successfully " + newUser.getEmail());
    }
}
