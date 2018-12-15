package net.findeasily.website.domain;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordForm {

    private String userId = "";

    @Length(min = 6)
    private String password = "";

    @NotEmpty
    private String passwordRepeated = "";

    @Override
    public String toString() {
        return "ResetPasswordForm{" +
                "user id = " + userId +
                ", password=***" + '\'' +
                ", passwordRepeated=***" + '\'' +
                '}';
    }

}
