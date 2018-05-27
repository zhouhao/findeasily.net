package net.findeasily.website.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

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

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView postRegistration() {
        return new ModelAndView("login");
    }

}
