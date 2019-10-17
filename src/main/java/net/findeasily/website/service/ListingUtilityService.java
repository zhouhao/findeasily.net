package net.findeasily.website.service;

import org.springframework.stereotype.Service;

import net.findeasily.website.repository.ListingUtilityRepository;

/**
 * @author Hao Zhou
 * @since 2018-08-08
 */
@Service
public class ListingUtilityService {

    private final ListingUtilityRepository listingUtilityRepository;

    public ListingUtilityService(ListingUtilityRepository listingUtilityRepository) {
        this.listingUtilityRepository = listingUtilityRepository;
    }

    public long deleteByListingId(String listingId) {
        return listingUtilityRepository.deleteByListingId(listingId);
    }
}
