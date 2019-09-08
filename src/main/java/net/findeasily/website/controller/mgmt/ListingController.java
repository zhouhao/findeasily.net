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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.CurrentUser;
import net.findeasily.website.domain.form.ListingCreateForm;
import net.findeasily.website.domain.validator.ListingCreateFormValidator;

@Controller
@Slf4j
public class ListingController {

    private final ListingCreateFormValidator listingCreateFormValidator;

    @Autowired
    public ListingController(ListingCreateFormValidator listingCreateFormValidator) {
        this.listingCreateFormValidator = listingCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(listingCreateFormValidator);
    }

    @GetMapping("/mgmt/listing/new")
    public ModelAndView createListing() {
        return new ModelAndView("listing/new", "form", new ListingCreateForm());
    }

    @PostMapping("/mgmt/listing/new")
    public String createListingHandler(@Valid @ModelAttribute("form") ListingCreateForm form, BindingResult bindingResult) {

        return "listing/new";
    }


    @GetMapping("/mgmt/listings")
    public ModelAndView myListings(Authentication authentication) {
        CurrentUser user = (CurrentUser) authentication.getPrincipal();
        Map<String, Object> model = new HashMap<>();

        return new ModelAndView("listing/new", model);
    }
}
