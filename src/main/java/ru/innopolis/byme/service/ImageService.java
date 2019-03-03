package ru.innopolis.byme.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.byme.dao.api.ImageDao;
import ru.innopolis.byme.entity.Image;
import ru.innopolis.byme.exception.ImageUploadException;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {
    @Autowired
    private ImageDao imageDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageService.class);
    @Value("${upload.location}")
    private String uploadDir;

    public void validateImageFile(MultipartFile image) throws ImageUploadException {
        LOGGER.info("Image size: " + image.getSize());
        LOGGER.info("Image content type: " + image.getContentType());
        String contentType = image.getContentType();
        if (!("image/jpeg".equals(contentType) || "image/gif".equals(contentType) || "image/png".equals(contentType))) {
            LOGGER.error("Only gif, png, jpeg images accepted");
            throw new ImageUploadException("image_error");
        }
    }

    public void saveImageFile(String filename, MultipartFile image) throws ImageUploadException {
        try {
            File file = new File(uploadDir + filename);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
            LOGGER.info("File saved {}", filename);
        } catch (IOException e) {
            LOGGER.error("File save error ", e);
            throw new ImageUploadException("Unable to save image", e);
        }
    }

    public void createImgById(int id, String imageName) {
        Image img = new Image();
        img.setImg(imageName);
        img.setAdId(id);
        img.setMain(true);
        imageDao.create(img);
    }

    public Image getImageByAd(int adId) {
        return imageDao.getImageByAd(adId);
    }

    public boolean exists(int adId) {
        return imageDao.exists(adId);
    }

    public void updateImgByAdId(int adId, String imageName) {
        Image img = imageDao.getImageByAd(adId);
        img.setImg(imageName);
        imageDao.update(img);
    }
}
