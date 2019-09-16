package net.findeasily.website.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.NonNull;
import net.findeasily.website.domain.form.ListingCreateForm;
import net.findeasily.website.entity.Listing;
import net.findeasily.website.repository.ListingRepository;

/**
 * @author Hao Zhou
 * @since 2018-08-08
 */
@Service
public class ListingService {

    private final ListingRepository listingRepository;

    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public Listing create(@NonNull ListingCreateForm form, @NonNull String ownerId) {
        Listing out = new Listing();
        out.setId(UUID.randomUUID().toString());
        out.setType(form.getCategory());
        out.setTitle(form.getTitle());
        out.setOwnerId(ownerId);
        out.setPropertyId(""); // Field not used, may be dropped from DB later

        out.setAvailableDate(form.getAvailableDateTS());
        out.setRentPrice(form.getMonthlyRent());
        out.setSecurityDeposit(form.getSecurityDeposit());
        out.setBathroomCount(form.getBathroomCount());
        out.setBedroomCount(form.getBedroomCount());

        out.setAddress1(form.getAddress1());
        out.setAddress2(form.getAddress2());
        out.setCity(form.getCity());
        out.setState(form.getState());
        out.setCountry(form.getCountry());
        out.setZip(form.getZipcode());
        out.setLatitude(form.getLatitude());
        out.setLongitude(form.getLongitude());

        out.setContactType(form.getContactType());
        out.setContactName(form.getContactName());
        out.setContactEmail(form.getContactEmail());
        out.setContactPhone(form.getContactPhone());

        out.setDescription(form.getDescription());

        return listingRepository.save(out);
    }

    public List<Listing> getByOwnerId(@NonNull String ownerId) {
        return listingRepository.getByOwnerId(ownerId);
    }

    public Listing getById(@NonNull String id) {
        return listingRepository.getById(id);
    }
}
