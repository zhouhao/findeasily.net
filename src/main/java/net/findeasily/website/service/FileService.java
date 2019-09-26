package net.findeasily.website.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import net.findeasily.website.config.Constant;
import net.findeasily.website.entity.User;
import net.findeasily.website.exception.StorageException;
import net.findeasily.website.exception.UnsupportedMediaTypeException;

@Service
@Slf4j
public class FileService {

    public enum Folder {
        USER_PICTURE("user", 400, 400),
        LISTING_PHOTO("listing", 1000, 800),
        ;
        private String path;
        @Getter
        private int width;
        @Getter
        private int height;

        public static Folder forValue(@NonNull String value) {
            for (Folder f : Folder.values()) {
                if (f.path.equalsIgnoreCase(value)) {
                    return f;
                }
            }
            return null;
        }

        Folder(String path, int width, int height) {
            this.path = path;
            this.width = width;
            this.height = height;
        }

        public String getPath() {
            return "images/" + this.path;
        }
    }

    @Value("${file.upload.path}")
    private Path fileRootPath;

    @Value("${image.filename.extension}")
    private String imageType;

    @PostConstruct
    public void init() throws IOException {
        if (!Files.exists(fileRootPath)) {
            Files.createDirectory(fileRootPath);
        }
        if (!Files.exists(fileRootPath) || !Files.isWritable(fileRootPath)) {
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

    public Path store(@NonNull MultipartFile file, Folder folder, String savedName) throws IOException {
        if (file.getOriginalFilename() == null) {
            throw new IOException("uploaded file is invalid, which does not have file name");
        }
        // we will only support PNG and JPG(JPEG) images now
        if (!Constant.IMAGE_CONTENT_TYPE.contains(file.getContentType())) {
            throw new UnsupportedMediaTypeException(file.getContentType() + " is not supported yet. Only PNG and JPG are supported now");
        }
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file " + filename);
        }
        if (filename.contains("..")) {
            // This is a security check
            throw new StorageException("Cannot store file with relative path outside current directory " + filename);
        }
        Path savedFile = fileRootPath.resolve(Paths.get(folder.getPath(), Constant.ORIGIN_IMAGE_PREFIX + savedName + "." + FilenameUtils.getExtension(filename)));
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, savedFile, StandardCopyOption.REPLACE_EXISTING);
        }

        // save a copy for site display
        File out = getFile(folder, savedName + imageType);
        Thumbnails.of(savedFile.toFile())
                .crop(Positions.CENTER)
                .size(folder.width, folder.height)
                .keepAspectRatio(true)
                .toFile(out);

        return out.toPath();
    }

    public Path storeUserPicture(@NonNull MultipartFile file, @NonNull User user) throws IOException {
        return store(file, Folder.USER_PICTURE, user.getId());
    }

    public Path storeListingPhoto(@NonNull MultipartFile file, @NonNull String listingId) throws IOException {
        return store(file, Folder.LISTING_PHOTO, UUID.randomUUID().toString());
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

    public File getFile(Folder folder, String file) {
        return fileRootPath.resolve(Paths.get(folder.getPath(), file)).toFile();
    }

    public File getFile(String file) {
        return fileRootPath.resolve(Paths.get(file)).toFile();
    }

    public File getUserPicture(@NonNull String userId) {
        return getFile(Folder.USER_PICTURE, userId + imageType);
    }
}

