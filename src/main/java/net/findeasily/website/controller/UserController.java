package net.findeasily.website.controller;

import java.security.Principal;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.CurrentUser;
import net.findeasily.website.domain.UserCreateForm;
import net.findeasily.website.domain.validator.UserCreateFormValidator;
import net.findeasily.website.service.UserService;

@Controller
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable UUID id, Principal principal) {
        log.debug("Getting user page for user={}", id);
        return new ModelAndView("user", "user", Optional.ofNullable(userService.getUserById(id.toString()))
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @RequestMapping("/user")
    public ModelAndView getSelfPage(Authentication authentication) {
        CurrentUser user = (CurrentUser) authentication.getPrincipal();
        return new ModelAndView("user", "user", user.getUser());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/user/create")
    public ModelAndView getUserCreatePage() {
        log.debug("Getting user create form");
        return new ModelAndView("user_create", "form", new UserCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/user/create")
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        log.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            log.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "user_create";
        }
        // ok, redirect
        return "redirect:/users";
    }

}
