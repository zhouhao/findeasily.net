package net.findeasily.website.service.currentuser;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.CurrentUser;
import net.findeasily.website.domain.Role;

@Service
@Slf4j
public class CurrentUserService {

    public boolean canAccessUser(CurrentUser currentUser, String userId) {
        log.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }

}
