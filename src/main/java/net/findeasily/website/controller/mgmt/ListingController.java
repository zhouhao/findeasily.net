package net.findeasily.website.controller.mgmt;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.CurrentUser;
import net.findeasily.website.domain.GenericResponse;
import net.findeasily.website.domain.form.ListingBasicInfoForm;
import net.findeasily.website.domain.validator.ListingCreateFormValidator;
import net.findeasily.website.entity.Listing;
import net.findeasily.website.service.FileService;
import net.findeasily.website.service.ListingPhotoService;
import net.findeasily.website.service.ListingService;
import net.findeasily.website.service.ListingUtilityService;

@Controller
@Slf4j
public class ListingController {

    private final ListingCreateFormValidator listingCreateFormValidator;
    private final ListingService listingService;
    private final FileService fileService;
    private final ListingPhotoService listingPhotoService;
    private final ListingUtilityService listingUtilityService;

    @Autowired
    public ListingController(ListingCreateFormValidator listingCreateFormValidator,
                             ListingService listingService, FileService fileService,
                             ListingPhotoService listingPhotoService,
                             ListingUtilityService listingUtilityService) {
        this.listingCreateFormValidator = listingCreateFormValidator;
        this.listingService = listingService;
        this.fileService = fileService;
        this.listingPhotoService = listingPhotoService;
        this.listingUtilityService = listingUtilityService;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(listingCreateFormValidator);
    }

    @GetMapping("/mgmt/listing/new")
    public ModelAndView createListing() {
        return new ModelAndView("listing/new", "form", new ListingBasicInfoForm());
    }

    @PreAuthorize("@currentUserService.canEditListing(#user, #listingId)")
    @GetMapping("/mgmt/listing/{id}/amenity-photo")
    public ModelAndView uploadPhoto(@PathVariable("id") String listingId, CurrentUser user) {
        Map<String, Object> model = new HashMap<>();
        model.put("id", listingId);
        model.put("photos", listingPhotoService.getByListingId(listingId));
        model.put("amenity", listingUtilityService.getByListingId(listingId));
        return new ModelAndView("listing/amenity-photo", model);
    }

    @PreAuthorize("@currentUserService.canEditListing(#user, #id)")
    @PostMapping("/mgmt/listing/{id}/photo")
    @ResponseBody
    public ResponseEntity<GenericResponse> uploadPhotoHandler(@RequestParam(value = "file") MultipartFile file,
                                                              @PathVariable("id") String id, CurrentUser user) throws IOException {
        Path path = fileService.storeListingPhoto(file, id);
        listingPhotoService.save(path.getFileName().toString(), id);
        return ResponseEntity.ok(new GenericResponse(true, "Photo is saved"));
    }

    @PreAuthorize("@currentUserService.canEditListing(#user, #listingId)")
    @DeleteMapping("/mgmt/listing/{listingId}/photo/{photoId}")
    @ResponseBody
    public ResponseEntity<GenericResponse> deletePhotoHandler(@PathVariable("listingId") String listingId,
                                                              @PathVariable("photoId") Integer photoId,
                                                              CurrentUser user) {
        return ResponseEntity.ok(new GenericResponse(listingPhotoService.delete(listingId, photoId), "Photo deletion request is done"));
    }

    @PostMapping("/mgmt/listing")
    public String createListingHandler(CurrentUser user,
                                       @Valid @ModelAttribute("form") ListingBasicInfoForm form,
                                       BindingResult bindingResult) {
        log.debug("Processing listing create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            log.error("errors happen when creating new listing...");
        }
        Listing listing = listingService.save(form, user.getId());
        return "redirect:/mgmt/listing/" + listing.getId();
    }


    @GetMapping("/mgmt/listings")
    public ModelAndView myListings(CurrentUser user) {
        Map<String, Object> model = new HashMap<>();
        model.put("listings", listingService.getByOwnerId(user.getId()));
        return new ModelAndView("listing/index", model);
    }

    @PreAuthorize("@currentUserService.canEditListing(#user, #id)")
    @GetMapping("/mgmt/listing/{id}")
    public ModelAndView listing(CurrentUser user, @PathVariable("id") String id) {
        Map<String, Object> model = new HashMap<>();
        Listing listing = listingService.getById(id);
        // if listing cannot be found by ID, then redirect to home page
        if (listing == null) {
            return new ModelAndView("redirect:/");
        }
        model.put("listing", listing);
        model.put("form", new ListingBasicInfoForm());
        return new ModelAndView("listing/edit", model);
    }

    @PreAuthorize("@currentUserService.canEditListing(#user, #listingId)")
    @DeleteMapping("/mgmt/listing/{id}")
    public ResponseEntity<GenericResponse> deleteListing(CurrentUser user, @PathVariable("id") String listingId) {
        return ResponseEntity.ok(new GenericResponse(listingService.delete(listingId), "Listing deletion request is done"));
    }
}
