package com.filmyai.backend.controller;

import com.filmyai.backend.dto.SignUp.SignUpRequestDto;
import com.filmyai.backend.dto.SignUp.SignUpResponseDto;
import com.filmyai.backend.service.SignUp.SignUpService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmyai")
public class SignUpController {


    private final SignUpService signUpService;

    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> registerUser(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto response = signUpService.registerUser(signUpRequestDto);
        return ResponseEntity.ok(response);
    }


}
