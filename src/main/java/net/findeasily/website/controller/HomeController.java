package net.findeasily.website.controller;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

    @RequestMapping("/")
    public String getHomePage() {
        log.debug("Getting home page");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error, HttpSession session) {
        log.debug("Getting login page, error={}", error);
        if (error.isPresent()) {
            log.error("ERROR = " + ((Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage());
        }
        return new ModelAndView("login", "error", error);
    }

    @RequestMapping(value = "/account_confirmation", method = RequestMethod.GET)
    public ModelAndView accountConfirmation(@RequestParam(name = "hash", defaultValue = "") String hash) {
        Map<String, String> model = new HashMap<>();
        if (StringUtils.isBlank(hash)) {
            model.put("toastr", "toastr.error('Invalid hash');");
        } else {
            String token = new String(Base64.getDecoder().decode(hash));
        }

        return new ModelAndView("login", model);
    }

}
