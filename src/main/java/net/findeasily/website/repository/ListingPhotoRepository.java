package net.findeasily.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import net.findeasily.website.entity.ListingPhoto;

public interface ListingPhotoRepository extends JpaRepository<ListingPhoto, Integer>, JpaSpecificationExecutor<ListingPhoto> {

}