package net.findeasily.website.controller.ajax;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.ForgetPasswordForm;
import net.findeasily.website.domain.User;
import net.findeasily.website.domain.UserCreateForm;
import net.findeasily.website.domain.dto.UserDto;
import net.findeasily.website.domain.validator.UserCreateFormValidator;
import net.findeasily.website.event.UserEvent;
import net.findeasily.website.event.publisher.UserEventPublisher;
import net.findeasily.website.exception.UserCreationException;
import net.findeasily.website.exception.WebApplicationException;
import net.findeasily.website.service.UserService;

@RestController
@Slf4j
public class PublicAjaxController {

    private final UserCreateFormValidator userCreateFormValidator;
    private final UserService userService;
    private final UserEventPublisher userEventPublisher;

    @Autowired
    public PublicAjaxController(UserCreateFormValidator userCreateFormValidator, UserService userService,
                                UserEventPublisher userEventPublisher) {
        this.userCreateFormValidator = userCreateFormValidator;
        this.userService = userService;
        this.userEventPublisher = userEventPublisher;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
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
        if (user != null) {
            userEventPublisher.publish(UserEvent.Type.ACCOUNT_CONFIRMATION, user);
            return ResponseEntity.ok(new UserDto(user));
        } else {
            throw new WebApplicationException("Failed to create new user");
        }
    }

    @RequestMapping(value = "/password/forget/handler", method = RequestMethod.POST)
    public ResponseEntity<Void> postForgetPasswordSubmit(@Valid @ModelAttribute("forgetPasswordForm") ForgetPasswordForm form, BindingResult bindingResult) {
        log.debug("Processing forget password. Email={}, bindingResult={}", form.getEmail(), bindingResult);

        if (bindingResult.hasErrors()) {
            // failed validation
            throw new UserCreationException(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList()));
        }

        User user = userService.getUserByEmail(form.getEmail());
        if (user != null) {
            userEventPublisher.publish(UserEvent.Type.PASSWORD_RESET_REQUEST, user);
            return ResponseEntity.ok().build();
        } else {
            throw new WebApplicationException("There is no existing user account associated with this email address");
        }
    }
}
