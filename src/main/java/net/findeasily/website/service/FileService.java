package net.findeasily.website.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.findeasily.website.exception.StorageException;

@Service
@Slf4j
public class FileService {

    public enum Folder {
        USER_PICTURE("images/user");
        @Getter
        private String path;

        Folder(String path) {
            this.path = path;
        }
    }

    @Value("${file.upload.path}")
    private Path fileRootPath;

    @PostConstruct
    public void init() {
        if (!fileRootPath.toFile().exists() || !Files.isWritable(fileRootPath)) {
            throw new StorageException(fileRootPath + " does not exist or writable");
        }

        try {
            for (Folder f : Folder.values()) {
                Path path = fileRootPath.resolve(f.getPath());
                if (!path.toFile().exists()) {
                    Files.createDirectories(path);
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new StorageException(e.getMessage());
        }
    }

    public boolean store(@NonNull MultipartFile file, Folder folder) throws IOException {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file " + filename);
        }
        if (filename.contains("..")) {
            // This is a security check
            throw new StorageException("Cannot store file with relative path outside current directory " + filename);
        }
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, fileRootPath.resolve(Paths.get(folder.getPath(), filename)), StandardCopyOption.REPLACE_EXISTING);
        }
        return true;
    }

    public Path load(@NonNull String filename) {
        return fileRootPath.resolve(filename);
    }

    public Resource loadAsResource(@NonNull String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new StorageException("Could not read file: " + filename, e);
        }
    }

}

