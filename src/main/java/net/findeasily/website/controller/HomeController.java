package net.findeasily.website.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.User;
import net.findeasily.website.domain.UserCreateForm;
import net.findeasily.website.domain.dto.UserDto;
import net.findeasily.website.domain.validator.UserCreateFormValidator;
import net.findeasily.website.exception.UserCreationException;
import net.findeasily.website.service.UserService;

@Controller
@Slf4j
public class HomeController {

    private final UserCreateFormValidator userCreateFormValidator;
    private final UserService userService;

    @Autowired
    public HomeController(UserCreateFormValidator userCreateFormValidator, UserService userService) {
        this.userCreateFormValidator = userCreateFormValidator;
        this.userService = userService;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
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

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public UserDto postRegistration(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        log.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            throw new UserCreationException(Joiner.on("|").join(bindingResult.getAllErrors()));
        }
        User user = null;
        try {
            user = userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            log.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            bindingResult.reject("username.exists", "Username already exists");
            return null;
        }
        // ok, redirect
        return new UserDto(user);
    }


    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getRegistration() {
        User user = userService.getUserByEmail("hzhou.me@gmail.com");
        return ResponseEntity.ok(new UserDto(user));
    }

}
