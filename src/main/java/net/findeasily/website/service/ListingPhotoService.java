package net.findeasily.website.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
    private final FileService fileService;

    public ListingPhotoService(ListingPhotoRepository listingPhotoRepository, FileService fileService) {
        this.listingPhotoRepository = listingPhotoRepository;
        this.fileService = fileService;
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

    public ListingPhoto getByListingIdAndPhotoId(String listingId, Integer photoId) {
        return listingPhotoRepository.findByListingIdAndId(listingId, photoId);
    }

    public boolean delete(ListingPhoto photo) {
        if (photo == null) {
            return false;
        }
        listingPhotoRepository.deleteById(photo.getId());
        return fileService.deleteFile(FileService.Folder.LISTING_PHOTO, photo.getPath());
    }

    public boolean deleteById(Integer id) {
        Optional<ListingPhoto> lp = listingPhotoRepository.findById(id);
        return lp.filter(this::delete).isPresent();
    }

    public boolean delete(String listingId, Integer photoId) {
        return delete(getByListingIdAndPhotoId(listingId, photoId));
    }

    public long deleteByListingId(String listingId) {
        // todo: delete photo files
        return listingPhotoRepository.deleteByListingId(listingId);
    }
}
