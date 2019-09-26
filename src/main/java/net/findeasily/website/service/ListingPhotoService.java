package net.findeasily.website.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import net.findeasily.website.entity.ListingPhoto;
import net.findeasily.website.repository.ListingPhotoRepository;

/**
 * @author Hao Zhou
 * @since 2018-08-08
 */
@Service
public class ListingPhotoService {

    private final ListingPhotoRepository listingPhotoRepository;

    public ListingPhotoService(ListingPhotoRepository listingPhotoRepository) {
        this.listingPhotoRepository = listingPhotoRepository;
    }

    public ListingPhoto save(@NonNull String fileName, @NonNull String listingId) {
        ListingPhoto out = new ListingPhoto();
        out.setListingId(listingId);
        out.setPath(fileName);
        out.setUploadDate(LocalDateTime.now());
        return listingPhotoRepository.save(out);
    }

    public List<ListingPhoto> getByListingId(String listingId) {
        return listingPhotoRepository.findByListingId(listingId);
    }
}
