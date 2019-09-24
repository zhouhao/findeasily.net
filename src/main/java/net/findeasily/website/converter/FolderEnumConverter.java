package net.findeasily.website.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.NonNull;
import net.findeasily.website.service.FileService;

@Component
public class FolderEnumConverter implements Converter<String, FileService.Folder> {
    @Override
    public FileService.Folder convert(@NonNull String s) {
        return FileService.Folder.forValue(s);
    }
}
