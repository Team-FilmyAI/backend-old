package com.filmyai.backend.service.ResetPassword;

import com.filmyai.backend.dto.PasswordReset.ResetPasswordRequestDTO;
import com.filmyai.backend.model.PasswordHistory;
import com.filmyai.backend.model.PasswordResetToken;
import com.filmyai.backend.model.User;
import com.filmyai.backend.repository.PasswordHistoryRepository;
import com.filmyai.backend.repository.PasswordResetTokenRepository;
import com.filmyai.backend.repository.UserRepository;
import com.filmyai.backend.service.SignUp.SignUpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PasswordResetTokenService {

    private PasswordResetTokenRepository passwordResetTokenRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JavaMailSender javaMailSender;
    private SignUpService signUpService;
    private PasswordHistoryRepository passwordHistoryRepository;

    @Value("${spring.mail.username}")
    private String fromEmailId;


    public PasswordResetTokenService(UserRepository userRepository, PasswordResetTokenRepository passwordResetTokenRepository, PasswordEncoder passwordEncoder, JavaMailSender javaMailSender, SignUpService signUpService,
                                     PasswordHistoryRepository passwordHistoryRepository) {
        this.userRepository = userRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
        this.signUpService = signUpService;
        this.passwordHistoryRepository = passwordHistoryRepository;
    }

    public void sendResetLink(String email) {

        User user = userRepository.findUserByEmail(email).orElseThrow(()
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

        SimpleMailMessage mailMessage = getSimpleMailMessage(email, user, resetLink);

        javaMailSender.send(mailMessage);

    }

    private SimpleMailMessage getSimpleMailMessage(String email, User user, String resetLink) {
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
        return mailMessage;
    }

    public User validateToken(String token) {
        PasswordResetToken prt = passwordResetTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid or Expired Token"));
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

    public Boolean Reset_Password(User user, ResetPasswordRequestDTO resetPasswordRequestDTO ) {

//        String newPassword = resetPasswordRequestDTO.New_Password();
//        String confirmPassword = resetPasswordRequestDTO.Confirm_Password();


//        if(resetPasswordRequestDTO.New_Password().equals(resetPasswordRequestDTO.Confirm_Password())){
//            user.setPassword(passwordEncoder.encode(resetPasswordRequestDTO.New_Password()));
//            userRepository.save(user);
//            return true;
//        }
//        else{
//            throw new IllegalArgumentException("Invalid Password");
//        }


        if(!(resetPasswordRequestDTO.New_Password()).equals(resetPasswordRequestDTO.Confirm_Password())) {
            throw new IllegalArgumentException("Passwords do not match.");
        }

        if (!signUpService.isPasswordStrong(resetPasswordRequestDTO.New_Password())) {
            throw new IllegalArgumentException("Password must be 8–64 characters long, with uppercase, lowercase, digit, special character, and no spaces.");
        }

        List<PasswordHistory> recentHistory = passwordHistoryRepository.findTop3ByUserOrderByChangedAtDesc(user);
        for (PasswordHistory history : recentHistory) {
            if (passwordEncoder.matches(resetPasswordRequestDTO.New_Password(), history.getPasswordHash())) {
                throw new IllegalArgumentException("You cannot reuse your last 3 passwords.");
            }
        }

        String encodedPassword = passwordEncoder.encode(resetPasswordRequestDTO.New_Password());
        user.setPassword(encodedPassword);
        userRepository.save(user);

        PasswordHistory newHistory = PasswordHistory.builder()
                .user(user)
                .passwordHash(encodedPassword)
                .changedAt(LocalDateTime.now())
                .build();
        passwordHistoryRepository.save(newHistory);

        //Keep only last 3 Passwords
        List<PasswordHistory> all = passwordHistoryRepository.findAllByUserOrderByChangedAtDesc(user);
        if (all.size() > 3) {
            passwordHistoryRepository.deleteAll(all.subList(3, all.size()));
        }

        return true;
    }
}