package com.filmyai.backend.repository;

import com.filmyai.backend.model.PasswordHistory;
import com.filmyai.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Long> {

    List<PasswordHistory> findTop3ByUserOrderByChangedAtDesc(User user);

    List<PasswordHistory> findAllByUserOrderByChangedAtDesc(User user);

    List<PasswordHistory> findByChangedAtBefore(LocalDateTime date);

}
