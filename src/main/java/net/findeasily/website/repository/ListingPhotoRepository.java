package net.findeasily.website.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import net.findeasily.website.entity.ListingPhoto;

public interface ListingPhotoRepository extends JpaRepository<ListingPhoto, Integer>, JpaSpecificationExecutor<ListingPhoto> {

    List<ListingPhoto> findByListingId(String listingId);
}