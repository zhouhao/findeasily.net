package net.findeasily.website.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.service.TokenService;

/**
 * Clean tokens which are created 12 hours ago
 */
@Component
@Slf4j
public class TokenCleanupTask {

    private static final int CLEAN_INTERVAL = 72 * 60 * 60 * 1000; // every 72 hours

    private final TokenService tokenService;

    @Autowired
    public TokenCleanupTask(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Scheduled(fixedRate = CLEAN_INTERVAL)
    public void cleanup() {
        int count = tokenService.deleteFromHourBefore(CLEAN_INTERVAL / (60 * 60 * 1000));
        log.info("clean " + count + " outdated token records...");
    }
}
