package net.findeasily.website.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import net.findeasily.website.domain.User;

@Getter
public class EmailEvent extends ApplicationEvent {

    private User user;
    private Type type;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public EmailEvent(Object source, Type type, User user) {
        super(source);
        this.type = type;
        this.user = user;
    }

    public enum Type {
        ACCOUNT_CONFIRMATION("account_confirmation.ftl", "Confirm your account"),
        PASSWORD_RESET_REQUEST("account_password_reset_request.ftl", "Request for password reset"),
        PASSWORD_RESET_COMPLETE("account_password_reset_complete.ftl", "Your password is reset");

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
