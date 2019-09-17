package net.findeasily.website.controller.mgmt;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.CurrentUser;
import net.findeasily.website.domain.form.ListingBasicInfoForm;
import net.findeasily.website.domain.validator.ListingCreateFormValidator;
import net.findeasily.website.entity.Listing;
import net.findeasily.website.service.ListingService;

@Controller
@Slf4j
public class ListingController {

    private final ListingCreateFormValidator listingCreateFormValidator;
    private final ListingService listingService;

    @Autowired
    public ListingController(ListingCreateFormValidator listingCreateFormValidator, ListingService listingService) {
        this.listingCreateFormValidator = listingCreateFormValidator;
        this.listingService = listingService;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(listingCreateFormValidator);
    }

    @GetMapping("/mgmt/listing/new")
    public ModelAndView createListing() {
        return new ModelAndView("listing/new", "form", new ListingBasicInfoForm());
    }

    @PostMapping("/mgmt/listing")
    public String createListingHandler(Authentication authentication,
                                       @Valid @ModelAttribute("form") ListingBasicInfoForm form,
                                       BindingResult bindingResult) {
        log.debug("Processing listing create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            log.error("errors happen when creating new listing...");
        }
        CurrentUser user = (CurrentUser) authentication.getPrincipal();
        listingService.create(form, user.getId());
        return "listing/new";
    }


    @GetMapping("/mgmt/listings")
    public ModelAndView myListings(Authentication authentication) {
        CurrentUser user = (CurrentUser) authentication.getPrincipal();
        Map<String, Object> model = new HashMap<>();
        model.put("listings", listingService.getByOwnerId(user.getId()));
        return new ModelAndView("listing/index", model);
    }

    @GetMapping("/mgmt/listing/{id}")
    public ModelAndView listing(Authentication authentication, @PathVariable("id") String id,
                                @Valid @ModelAttribute("form") ListingBasicInfoForm form, BindingResult bindingResult) {
        CurrentUser user = (CurrentUser) authentication.getPrincipal();
        Map<String, Object> model = new HashMap<>();
        Listing listing = listingService.getById(id);
        // if listing cannot be found by ID, then redirect to home page
        if (listing == null) {
            return new ModelAndView("redirect:/");
        }
        model.put("listing", listing);
        return new ModelAndView("listing/edit", model);
    }
}
