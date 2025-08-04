package com.filmyai.backend.service.ResetPassword;

import com.filmyai.backend.model.PasswordHistory;
import com.filmyai.backend.repository.PasswordHistoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PasswordCleanupService {

    private final PasswordHistoryRepository passwordHistoryRepository;

    @Scheduled(cron = "0 0 3 * * ?")
    @Transactional
    public void deleteOldPassword() {
        LocalDateTime sixMonthsAgo = LocalDateTime.now().minusMonths(6);
        List<PasswordHistory> oldEntries = passwordHistoryRepository.findByChangedAtBefore(sixMonthsAgo);
        if (!oldEntries.isEmpty()) {
            passwordHistoryRepository.deleteAll(oldEntries);
            log.info("Deleted {} password histories older than 6 months", oldEntries.size());
        }
    }
}
