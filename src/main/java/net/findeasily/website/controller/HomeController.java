package net.findeasily.website.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

    public static final String USER_ID_KEY = "user_id";
    public static final String TOKEN_ID_KEY = "token_id";
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

    @GetMapping("/login")
    public ModelAndView getLoginPage(@RequestParam Optional<String> error, HttpSession session) {
        log.debug("Getting login page, error={}", error);
        if (error.isPresent()) {
            log.error("ERROR = " + ((Exception) session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION")).getMessage());
        }
        return new ModelAndView("login", "error", error);
    }

    @GetMapping("/account_confirmation")
    public ModelAndView accountConfirmation(@RequestParam(name = "hash", defaultValue = "") String hash) {
        Map<String, String> model = new HashMap<>();
        Token token = tokenService.parse(hash);
        if (token == null) {
            model.put(ToastrUtils.KEY, ToastrUtils.error("Url is expired or not valid"));
        } else if (userService.activate(token.getUserId())) {
            model.put(ToastrUtils.KEY, ToastrUtils.success("Activation succeeded, please login now"));
            tokenService.removeById(token.getId());
        } else {
            model.put(ToastrUtils.KEY, ToastrUtils.error("Activation failed, please contact our support team"));
        }

        model.put("pageTitle", "Account Confirmation");

        return new ModelAndView("login", model);
    }

    @GetMapping("/password/forget")
    public ModelAndView getForgetPassword() {
        return new ModelAndView("forget_password");
    }

    @GetMapping("/password/reset")
    public ModelAndView getResetPassword(@RequestParam(name = "hash", defaultValue = "") String hash, HttpSession session) {
        Map<String, String> model = new HashMap<>();
        Token token = tokenService.parse(hash);
        if (token == null) {
            model.put(ToastrUtils.KEY, ToastrUtils.error("Url is expired or not valid"));
            log.debug("Processing password reset link. token is null");
        } else if (userService.getUserById(token.getUserId()) != null) {
            log.debug("Processing password reset link. userId={}", token.getUserId());
            session.setAttribute(USER_ID_KEY, token.getUserId());
            session.setAttribute(TOKEN_ID_KEY, token.getId());
            return new ModelAndView("user/reset_password", model);
        } else {
            model.put(ToastrUtils.KEY, ToastrUtils.error("Activation failed, please contact our support team"));
        }
        return new ModelAndView("user/forget_password", model); // todo: redirect?
    }

    // TODO: this is for test purpose, just render email template for review
    @GetMapping("/password/email")
    public ModelAndView email() {
        Map<String, Object> model = new HashMap<>();
        model.put("token", "1234");
        model.put("webServer", "http://127.0.0.1:8080/");
        return new ModelAndView("email/account_password_reset_complete", model);
    }

}
