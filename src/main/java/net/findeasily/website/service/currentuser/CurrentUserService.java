package net.findeasily.website.service.currentuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.CurrentUser;
import net.findeasily.website.domain.Role;
import net.findeasily.website.entity.Listing;
import net.findeasily.website.service.ListingService;

@Service
@Slf4j
public class CurrentUserService {

    private final ListingService listingService;

    @Autowired
    public CurrentUserService(ListingService listingService) {
        this.listingService = listingService;
    }

    public boolean canAccessUser(CurrentUser currentUser, String userId) {
        log.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null && (currentUser.getRole() == Role.ADMIN || currentUser.getId().equals(userId));
    }

    public boolean canEditListing(CurrentUser currentUser, String listingId) {
        Listing listing = listingService.getById(listingId);
        return currentUser != null && (currentUser.getRole() == Role.ADMIN
                || (listing != null && listing.getOwnerId().equals(currentUser.getId())));
    }

}
