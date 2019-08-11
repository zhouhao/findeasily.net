package net.findeasily.website.controller.mgmt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.CurrentUser;

@Controller
@Slf4j
public class ListingController {

    // TODO
    @GetMapping("/mgmt/listing/new")
    public ModelAndView createListing(Authentication authentication) {
        CurrentUser user = (CurrentUser) authentication.getPrincipal();
        Map<String, Object> model = new HashMap<>();

        return new ModelAndView("listing/new", model);
    }
}
