package net.findeasily.website.domain;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ResetPasswordForm {

    private String userId = "";

    @Length(min = 6)
    private String password = "";

    @NotEmpty
    private String passwordRepeated = "";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordRepeated() {
        return passwordRepeated;
    }

    public void setPasswordRepeated(String passwordRepeated) {
        this.passwordRepeated = passwordRepeated;
    }

    @Override
    public String toString() {
        return "ResetPasswordForm{" +
                "user id = " + userId +
                ", password=***" + '\'' +
                ", passwordRepeated=***" + '\'' +
                '}';
    }

}
