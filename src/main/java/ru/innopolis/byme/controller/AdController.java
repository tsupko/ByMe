package ru.innopolis.byme.controller;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.byme.dao.AdDao;
import ru.innopolis.byme.dao.CategoryDao;
import ru.innopolis.byme.dao.ImageDao;
import ru.innopolis.byme.dao.UserDao;
import ru.innopolis.byme.entity.Ad;
import ru.innopolis.byme.entity.Image;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.exception.ImageUploadException;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

@Controller
public class AdController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdController.class);

    private final AdDao adDao;
    private final UserDao userDao;
    private final CategoryDao categoryDao;
    private final ImageDao imageDao;
    @Value("${upload.location}")
    private String fileUpload;

    public AdController(AdDao adDao, UserDao userDao, CategoryDao categoryDao, ImageDao imageDao) {
        this.adDao = adDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.imageDao = imageDao;
    }

    @RequestMapping(value = "/ad", method = RequestMethod.GET)
    public String ad(Model model) {
        LOGGER.info("mapping get /ad");
        model.addAttribute("categories", categoryDao.getAll());
        model.addAttribute("ad", new Ad());
        return "ad";
    }

    @RequestMapping(value = "/ad", method = RequestMethod.POST)
    public String addAd(@ModelAttribute("ad") Ad ad, MultipartFile image,
                        BindingResult bindingResult, Principal principal) {
        String login = principal.getName();
        LOGGER.info("mapping post /ad, login: {}", login);
        User user = userDao.selectByLogin(login).get();
        ad.setUserId(user.getId());
        ad.setConfirm(true);
        ad.setActual(true);
        LOGGER.info("Новое объявление: {}", ad);
        adDao.create(ad);
        LOGGER.info("File name: " + image.getName());
        LOGGER.info("File size: " + image.getSize());
        LOGGER.info("File content type: " + image.getContentType());
        LOGGER.info("image.isEmpty(): " + image.isEmpty());
        try {
            if (!image.isEmpty()) {
                validateImage(image);
                String imageName = ad.getId() + ".jpg";
                saveImage(imageName, image);
                Image img = new Image();
                img.setImg(imageName);
                img.setAdId(ad.getId());
                img.setMain(true);
                LOGGER.info("Новое фото объявление: {}", img);
                imageDao.create(img);
            }
        } catch (ImageUploadException e) {
            bindingResult.reject(e.getMessage());
            return "ad";
        }
        return "redirect:/";
    }

    private void validateImage(MultipartFile image) throws ImageUploadException {
        if (!image.getContentType().equals("image/jpeg")) {
            LOGGER.error("Only JPG images accepted");
            throw new ImageUploadException("Only JPG images accepted");
        }
    }

    private void saveImage(String filename, MultipartFile image) throws ImageUploadException {
        try {
            File file = new File(fileUpload + filename);
            FileUtils.writeByteArrayToFile(file, image.getBytes());
        } catch (IOException e) {
            LOGGER.error("File save error ", e);
            throw new ImageUploadException("Unable to save image", e);
        }
    }
}
