package com.filmyai.backend.repository;

import com.filmyai.backend.model.PasswordResetToken;
import com.filmyai.backend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

    Optional<PasswordResetToken> findByToken(String token);
    Optional<PasswordResetToken> findByUserAndUsedIsFalse(Users user);
}
