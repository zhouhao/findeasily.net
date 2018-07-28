package net.findeasily.website.domain.validator;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.ResetPasswordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
@Slf4j
public class ResetPasswordFormValidator implements Validator {

    @Autowired
    public ResetPasswordFormValidator() {
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ResetPasswordForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.debug("Validating {}", target);
        ResetPasswordForm form = (ResetPasswordForm) target;
        validatePasswords(errors, form);
    }

    private void validatePasswords(Errors errors, ResetPasswordForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

}
