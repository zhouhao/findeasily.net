package net.findeasily.website.service;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.NonNull;

@Service
public class FreemarkerService {
    @Value("${static.resource.host}")
    private String resourceHost;

    public String getListingImage(String filename) {
        return resourceHost + Paths.get(FileService.Folder.LISTING_PHOTO.getPath(), filename).toString();
    }

    public String getUserImage(@NonNull String filename) {
        if (!filename.endsWith(".png")) {
            filename += ".png";
        }
        return resourceHost + Paths.get(FileService.Folder.USER_PICTURE.getPath(), filename).toString();
    }
}
