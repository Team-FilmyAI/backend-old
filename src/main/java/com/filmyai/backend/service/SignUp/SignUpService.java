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

    public boolean isPasswordStrong(String password) {
        if (password == null) return false;

        // Explanation:
        // ^              → Start of string
        // (?=.*[0-9])    → At least one digit
        // (?=.*[a-z])    → At least one lowercase letter
        // (?=.*[A-Z])    → At least one uppercase letter
        // (?=.*[^a-zA-Z0-9]) → At least one special character
        // (?=\\S+$)      → No whitespace
        // .{8,64}          → At least 8 characters, and max 64 Characters
        // $              → End of string

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?=\\S+$).{8,64}$";
        return password.matches(regex);
    }


    public SignUpResponseDto registerUser(SignUpRequestDto requestDto) {
        if (userRepository.findUsersByEmail(requestDto.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already in use");
        }


        if (!isPasswordStrong(requestDto.getPassword())) {
            throw new IllegalArgumentException("Password must be at least 8 characters long and include uppercase, lowercase, digit, special character, and no spaces.");
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
