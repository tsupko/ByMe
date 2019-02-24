package ru.innopolis.byme.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.byme.exception.ImageUploadException;

import java.io.File;
import java.io.IOException;

@Component
public class ImageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);
    @Value("${upload.location}")
    private String uploadDir;

    public void validateImage(MultipartFile image) throws ImageUploadException {
        if (!image.getContentType().equals("image/jpeg")) {
            LOGGER.error("Only JPG images accepted");
            throw new ImageUploadException("Only JPG images accepted");
        }
    }

    public void saveImage(String filename, MultipartFile image) throws ImageUploadException {
        try {
            File file = new File(uploadDir + filename);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
            LOGGER.info("File saved {}", filename);
        } catch (IOException e) {
            LOGGER.error("File save error ", e);
            throw new ImageUploadException("Unable to save image", e);
        }
    }
}
