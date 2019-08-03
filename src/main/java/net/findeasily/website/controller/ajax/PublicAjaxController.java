package net.findeasily.website.controller.ajax;

import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.controller.HomeController;
import net.findeasily.website.domain.dto.UserDto;
import net.findeasily.website.domain.form.ForgetPasswordForm;
import net.findeasily.website.domain.form.ResetPasswordForm;
import net.findeasily.website.domain.form.UserCreateForm;
import net.findeasily.website.domain.validator.ResetPasswordFormValidator;
import net.findeasily.website.domain.validator.UserCreateFormValidator;
import net.findeasily.website.entity.User;
import net.findeasily.website.event.EmailEvent;
import net.findeasily.website.exception.UserCreationException;
import net.findeasily.website.exception.WebApplicationException;
import net.findeasily.website.service.TokenService;
import net.findeasily.website.service.UserService;

@RestController
@Slf4j
public class PublicAjaxController {

    private final UserCreateFormValidator userCreateFormValidator;
    private final ResetPasswordFormValidator resetPasswordFormValidator;
    private final UserService userService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public PublicAjaxController(UserCreateFormValidator userCreateFormValidator, UserService userService,
                                ResetPasswordFormValidator resetPasswordFormValidator,
                                PasswordEncoder passwordEncoder, TokenService tokenService,
                                ApplicationEventPublisher applicationEventPublisher) {
        this.userCreateFormValidator = userCreateFormValidator;
        this.userService = userService;
        this.tokenService = tokenService;
        this.resetPasswordFormValidator = resetPasswordFormValidator;
        this.passwordEncoder = passwordEncoder;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @InitBinder("resetPasswordForm")
    public void initResetPasswordFormBinder(WebDataBinder binder) {
        binder.addValidators(resetPasswordFormValidator);
    }

    @PostMapping("/signup")
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
            applicationEventPublisher.publishEvent(new EmailEvent(this, EmailEvent.Type.ACCOUNT_CONFIRMATION, user));
            return ResponseEntity.ok(new UserDto(user));
        } else {
            throw new WebApplicationException("Failed to create new user");
        }
    }

    @PostMapping("/password/forget/handler")
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
            applicationEventPublisher.publishEvent(new EmailEvent(this, EmailEvent.Type.PASSWORD_RESET_REQUEST, user));
            return ResponseEntity.ok().build();
        } else {
            throw new WebApplicationException("There is no existing user account associated with this email address");
        }
    }

    @PostMapping("/password/reset/handler")
    public ResponseEntity<UserDto> postResetPasswordSubmit(@Valid @ModelAttribute("resetPasswordForm") ResetPasswordForm form,
                                                           BindingResult bindingResult, HttpSession session) {
        log.debug("Processing reset password. userId={}, bindingResult={}", form.getUserId(), bindingResult);

        if (bindingResult.hasErrors()) {
            // failed validation
            throw new UserCreationException(bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList()));
        }

        User user = userService.getUserById(String.valueOf(session.getAttribute(HomeController.USER_ID_KEY)));
        if (user != null) {
            user.setPassword(passwordEncoder.encode(form.getPassword()));
            if (userService.updateById(user)) {
                applicationEventPublisher.publishEvent(new EmailEvent(this, EmailEvent.Type.PASSWORD_RESET_COMPLETE, user));
                int tokenId = (int) session.getAttribute(HomeController.TOKEN_ID_KEY);
                tokenService.removeById(tokenId);
                return ResponseEntity.ok(new UserDto(user));
            }
        }
        throw new WebApplicationException("Something went wrong.");
    }
}
