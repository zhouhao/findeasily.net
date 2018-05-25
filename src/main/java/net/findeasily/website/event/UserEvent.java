package net.findeasily.website.event;

import org.springframework.context.ApplicationEvent;

import net.findeasily.website.domain.User;

public abstract class UserEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserEvent(Object source, Type type, User user) {
        super(source);
    }

    public enum Type {
        ACCOUNT_CONFIRMATION("account_confirmation.ftl", "Confirm your account"),
        PASSOWRD_RESET_REQUEST("account_password_reset_request.ftl", "Request for password reset"),
        PASSOWRD_RESET_COMPLETE("account_password_reset_complete.ftl", "Your password is reset");

        private String templateFile;
        private String subject;

        Type(String templateFile, String subject) {
            this.templateFile = templateFile;
            this.subject = subject;
        }

        public String getTemplateFile() {
            return templateFile;
        }

        public String getSubject() {
            return subject;
        }
    }
}
