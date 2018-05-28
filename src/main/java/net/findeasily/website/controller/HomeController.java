package net.findeasily.website.controller;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
    public ResponseEntity<UserDto> postRegistration(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        log.debug("Processing user create form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            throw new UserCreationException(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        User user = userService.create(form);
        return ResponseEntity.ok(new UserDto(user));
    }

}
