package net.findeasily.website.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.service.TokenService;

/**
 * Clean tokens which are created 72 hours ago every 12 hours
 */
@Component
@Slf4j
public class TokenCleanupTask {

    private static final int CLEAN_INTERVAL = 12 * 60 * 60 * 1000; // every 12 hours

    private final TokenService tokenService;

    @Autowired
    public TokenCleanupTask(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Scheduled(fixedRate = CLEAN_INTERVAL)
    public void cleanup() {
        long count = tokenService.deleteFromHourBefore(6 * CLEAN_INTERVAL / (60 * 60 * 1000)); // clean tokens generated 72 hours before
        log.info("clean " + count + " outdated token records...");
    }
}
