package net.findeasily.website.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.Token;
import net.findeasily.website.service.TokenService;
import net.findeasily.website.service.UserService;
import net.findeasily.website.util.ToastrUtils;

@Controller
@Slf4j
public class HomeController {

    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public HomeController(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

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
            model.put(ToastrUtils.KEY, ToastrUtils.error("Invalid hash, should not be empty"));
        } else {
            Pair<String, Token> pair = tokenService.parse(hash);
            if (!pair.equals(ImmutablePair.nullPair()) &&
                    tokenService.match(pair.getRight(), pair.getLeft()) &&
                    userService.activate(pair.getRight().getUserId())) {
                model.put(ToastrUtils.KEY, ToastrUtils.success("Activation succeeded, please login now"));
            } else {
                model.put(ToastrUtils.KEY, ToastrUtils.error("Activation failed, please contact our support team"));
            }
        }
        model.put("pageTitle", "Account Confirmation");

        return new ModelAndView("login", model);
    }

    @RequestMapping(value = "/password/forget", method = RequestMethod.GET)
    public ModelAndView getForgetPassword() {
        return new ModelAndView("forget_password");
    }

    @GetMapping("/password/email")
    public ModelAndView email() {
        Map<String, Object> model = new HashMap<>();
        model.put("token", "1234");
        model.put("webServer", "http://127.0.0.1:8080/");
        return new ModelAndView("email/account_confirmation", model);
    }

}
