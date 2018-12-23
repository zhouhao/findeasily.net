package net.findeasily.website.event.listener;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.findeasily.website.event.ImageUploadedEvent;
import net.findeasily.website.service.FileService;

@Component
@Slf4j
public class GeneralEventListener {

    private final FileService fileService;

    @Autowired
    public GeneralEventListener(FileService fileService) {
        this.fileService = fileService;
    }

    @EventListener
    public void handleImageUploadedEvent(ImageUploadedEvent event) throws IOException {
        log.info("ImageUploadedEvent = {}", event);
        Thumbnails.of(event.getOriginImagePath())
                .crop(Positions.CENTER)
                .size(400, 400)
                .keepAspectRatio(true)
                .toFile(fileService.getUserPicture(event.getUserId()));
    }

}
