package net.findeasily.website.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(value = "/public/images/{type}/{path}", produces = MediaType.IMAGE_PNG_VALUE)
    public void getImage(HttpServletResponse response,
                         @PathVariable("type") FileService.Folder type,
                         @PathVariable("path") String path) throws IOException {
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        File file = fileService.getFile(type, path);
        if (file.exists()) {
            try (InputStream is = new FileInputStream(file)) {
                StreamUtils.copy(is, response.getOutputStream());
            }
        }
        //if file not exists, return empty response is fine
    }

    @GetMapping("/contact")
    public ModelAndView contact() {
        return new ModelAndView("public/contact");
    }
}
