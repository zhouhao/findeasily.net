package net.findeasily.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import net.findeasily.website.entity.ListingUtility;

public interface ListingUtilityRepository extends JpaRepository<ListingUtility, String>, JpaSpecificationExecutor<ListingUtility> {

    long deleteByListingId(String listingId);

    ListingUtility getByListingId(String listingId);
}