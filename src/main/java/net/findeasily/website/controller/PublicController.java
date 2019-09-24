package net.findeasily.website.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.service.FileService;

@Controller
@Slf4j
public class PublicController {

    private final FileService fileService;

    @Autowired
    public PublicController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping(value = "/public/user/{userId}/avatar", produces = MediaType.IMAGE_PNG_VALUE)
    public void getImage(HttpServletResponse response, @PathVariable("userId") UUID userId) throws IOException {
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        File file = fileService.getUserPicture(userId.toString());
        try (InputStream is = file.exists() ? new FileInputStream(file) :
                new ClassPathResource("static/images/avatar/avatar-bg.png").getInputStream()) {
            StreamUtils.copy(is, response.getOutputStream());
        }
    }

    @GetMapping(value = "/public/images/{pathBase64}", produces = MediaType.IMAGE_PNG_VALUE)
    public void getImage(HttpServletResponse response, @PathVariable("pathBase64") String pathBase64) throws IOException {
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        String path = new String(Base64.getDecoder().decode(pathBase64));
        log.debug("path = {}", path);
        File file = fileService.getFile(path);
        if (file.exists()) {
            try (InputStream is = new FileInputStream(file)) {
                StreamUtils.copy(is, response.getOutputStream());
            }
        }
        // TODO: if file not exists
    }

    @GetMapping("/contact")
    public ModelAndView contact() {
        return new ModelAndView("public/contact");
    }
}
