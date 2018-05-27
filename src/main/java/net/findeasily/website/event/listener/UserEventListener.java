package net.findeasily.website.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.event.UserEvent;

@Component
@Slf4j
public class UserEventListener {
    @EventListener
    public void handleContextRefresh(UserEvent event) {
        log.info(event.getType() + " - " + event.getUser());
    }
}
