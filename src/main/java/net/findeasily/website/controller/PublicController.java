package net.findeasily.website.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        InputStream is = null;
        if (!file.exists()) {
            ClassPathResource imgFile = new ClassPathResource("static/images/avatar/avatar-bg.png");
            is = imgFile.getInputStream();
        } else {
            is = new FileInputStream(file);
        }

        StreamUtils.copy(is, response.getOutputStream());
        is.close();
    }

    @GetMapping("/contact")
    public ModelAndView contact() {
        return new ModelAndView("public/contact");
    }
}
