package com.filmyai.backend.controller;

import com.filmyai.backend.dto.PasswordReset.ForgotPasswordRequestDTO;
import com.filmyai.backend.dto.PasswordReset.ResetPasswordRequestDTO;
import com.filmyai.backend.model.Users;
import com.filmyai.backend.service.ResetPassword.PasswordResetTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/filmyai")
public class PasswordResetController {

    private PasswordResetTokenService passwordResetTokenService;


    public PasswordResetController(PasswordResetTokenService passwordResetTokenService) {
        this.passwordResetTokenService = passwordResetTokenService;
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO){
        passwordResetTokenService.sendResetLink(forgotPasswordRequestDTO.email());
        return ResponseEntity.ok("If your email exists, a password reset link will be sent.");
    }

    @PostMapping("/Reset-Password")
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody ResetPasswordRequestDTO resetPasswordRequestDTO){

        Users user = passwordResetTokenService.validateToken(token);
        Boolean flag = passwordResetTokenService.Reset_Password(user, resetPasswordRequestDTO);
        if(flag){
            passwordResetTokenService.markTokenUsed(token);
            passwordResetTokenService.DeleteToken(token);
        }
        return ResponseEntity.ok("Password was reset successfully.");
    }
}
