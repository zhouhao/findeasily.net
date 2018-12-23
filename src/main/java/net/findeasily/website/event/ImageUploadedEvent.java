package net.findeasily.website.event;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ImageUploadedEvent extends ApplicationEvent {

    private String originImagePath;
    private String userId;

    public ImageUploadedEvent(Object source, String originImagePath, String userId) {
        super(source);
        this.originImagePath = originImagePath;
        this.userId = userId;
    }
}
