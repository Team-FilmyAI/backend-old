package com.filmyai.backend.controller;

import com.filmyai.backend.dto.Login.LoginRequestDto;
import com.filmyai.backend.dto.Login.LoginResponseDto;
import com.filmyai.backend.service.Login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filmyai")
public class LoginController {


    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        LoginResponseDto response = loginService.login(loginRequestDto);
        return ResponseEntity.ok(response);
    }



}
