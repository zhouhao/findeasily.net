package net.findeasily.website.domain.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.form.ListingCreateForm;

@Component
@Slf4j
public class ListingCreateFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ListingCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.debug("Validating {}", target);
        ListingCreateForm form = (ListingCreateForm) target;

        validateAddress(errors, form);
    }

    private void validateAddress(Errors errors, ListingCreateForm form) {
        errors.reject("address.empty", "Address info cannot be empty");
    }

}
