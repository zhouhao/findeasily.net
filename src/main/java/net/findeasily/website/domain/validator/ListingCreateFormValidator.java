package net.findeasily.website.domain.validator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.domain.form.ListingCreateForm;

@Component
@Slf4j
public class ListingCreateFormValidator implements Validator {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(ListingCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.debug("Validating {}", target);
        ListingCreateForm form = (ListingCreateForm) target;
        log.info("form = {}", form);
        validateContact(errors, form);
    }

    private void validateAvailableDate(Errors errors, ListingCreateForm form) {
        try {
            form.setAvailableDateTS(LocalDateTime.from(formatter.parse(form.getAvailableDate())).toLocalDate());
        } catch (Exception e) {
            errors.reject("available_date.wrong_format", form.getAvailableDate() + " is in wrong format, correct example should be 2019-09-28");
        }
    }

    private void validateContact(Errors errors, ListingCreateForm form) {
        if (StringUtils.isAnyBlank(form.getContactType(), form.getContactName())
                || StringUtils.isAllBlank(form.getContactPhone(), form.getContactEmail())) {
            errors.reject("contact.notCompleted", "Contact name and either phone or email are required");
        }
    }


}
