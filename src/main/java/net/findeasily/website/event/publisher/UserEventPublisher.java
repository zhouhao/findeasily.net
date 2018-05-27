package net.findeasily.website.event.publisher;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import net.findeasily.website.domain.User;
import net.findeasily.website.event.UserEvent;

@Component
public class UserEventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public UserEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(UserEvent.Type type, @NotNull User user) {
        UserEvent userEvent = new UserEvent(this, type, user);
        applicationEventPublisher.publishEvent(userEvent);
    }
}
