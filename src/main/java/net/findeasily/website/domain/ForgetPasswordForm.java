package net.findeasily.website.domain;

import javax.validation.constraints.Email;

public class ForgetPasswordForm {
    @Email
    private String email = "";

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ForgetPasswordForm{" +
                "email='" + email.replaceFirst("@.+", "@***") + '\'' +
                '}';
    }

}
