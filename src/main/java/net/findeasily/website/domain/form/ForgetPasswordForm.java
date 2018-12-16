package net.findeasily.website.domain.form;

import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgetPasswordForm {
    @Email
    private String email;

    @Override
    public String toString() {
        return "ForgetPasswordForm{" +
                "email='" + email.replaceFirst("@.+", "@***") + '\'' +
                '}';
    }

}
