package com.filmyai.backend.service.ResetPassword;

import com.filmyai.backend.dto.PasswordReset.ResetPasswordRequestDTO;
import com.filmyai.backend.enums.Role;
import com.filmyai.backend.model.PasswordResetToken;
import com.filmyai.backend.model.Users;
import com.filmyai.backend.repository.PasswordResetTokenRepository;
import com.filmyai.backend.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetTokenService {

    private PasswordResetTokenRepository passwordResetTokenRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JavaMailSender javaMailSender;

    @Value("$(spring.mail.username)")
    private String fromEmailId;


    public PasswordResetTokenService(UserRepository userRepository, PasswordResetTokenRepository passwordResetTokenRepository,  PasswordEncoder passwordEncoder, JavaMailSender javaMailSender) {
        this.userRepository = userRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }

    public void sendResetLink(String email) {

        Users user = userRepository.findUsersByEmail(email).orElseThrow(()
        -> new UsernameNotFoundException("User not found with email: " + email));

        String token = UUID.randomUUID().toString();
        PasswordResetToken prt = PasswordResetToken.builder()
                .user(user)
                .token(token)
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .build();

        passwordResetTokenRepository.save(prt);

        String restEndpoint = "https://filmyai/Reset-Password?token=";
        String resetLink = restEndpoint + token;

        String emailBody = String.format("""
        Hi %s,
    
        We received a request to reset your password for your Filmy AI account.
    
        Please click the link below to reset your password. This link is valid for 15 minutes only:
    
        %s
    
        If you did not request this, please ignore this email. Your password will remain unchanged.
    
        Thanks,
        The Filmy AI Team
        """, user.getFirstName(), resetLink);

        // Send Email Address
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(fromEmailId);
        mailMessage.setTo(email);
        mailMessage.setSubject("Reset Password link for FilmyAI");
        mailMessage.setText(emailBody);

        javaMailSender.send(mailMessage);

    }

    public Users validateToken(String token) {
        PasswordResetToken prt = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invaild or Expired Token"));
        if (prt.isUsed() || prt.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Token is expired or already used");
        }
        return prt.getUser();
    }

    public void markTokenUsed(String token) {
        PasswordResetToken prt = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Token"));
        prt.setUsed(true);
        passwordResetTokenRepository.save(prt);
    }

    public void DeleteToken(String token) {
        PasswordResetToken prt = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Token"));
        passwordResetTokenRepository.delete(prt);
    }

    public Boolean Reset_Password(Users user, ResetPasswordRequestDTO resetPasswordRequestDTO ) {
        if(resetPasswordRequestDTO.New_Password().equals(resetPasswordRequestDTO.Confirm_Password())){
            user.setPassword(passwordEncoder.encode(resetPasswordRequestDTO.New_Password()));
            userRepository.save(user);
            return true;
        }
        else{
            throw new IllegalArgumentException("Invalid Password");
        }
    }
}