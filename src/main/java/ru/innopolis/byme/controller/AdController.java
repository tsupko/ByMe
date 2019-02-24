package ru.innopolis.byme.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import ru.innopolis.byme.service.ImageService;

import java.security.Principal;

@Controller
@RequestMapping("/ad")
public class AdController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdController.class);

    private final AdDao adDao;
    private final UserDao userDao;
    private final CategoryDao categoryDao;
    private final ImageDao imageDao;
    private final ImageService imageService;

    public AdController(AdDao adDao, UserDao userDao, CategoryDao categoryDao, ImageDao imageDao,
                        ImageService imageService) {
        this.adDao = adDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
        this.imageDao = imageDao;
        this.imageService = imageService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String ad(Model model) {
        LOGGER.info("mapping get /ad/new");
        model.addAttribute("categories", categoryDao.getAll());
        model.addAttribute("ad", new Ad());
        model.addAttribute("submit", "Добавить объявление");
        return "ad";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addAd(@ModelAttribute("ad") Ad ad, MultipartFile image,
                        BindingResult bindingResult, Principal principal) {
        String login = principal.getName();
        LOGGER.info("mapping post /ad/new, login: {}", login);
        User user = userDao.selectByLogin(login).get();
        ad.setUserId(user.getId());
        ad.setConfirm(true);
        ad.setActual(true);
        adDao.create(ad);
        LOGGER.info("Новое объявление: {}", ad);
        LOGGER.info("image.isEmpty(): " + image.isEmpty());
        try {
            if (!image.isEmpty()) {
                LOGGER.info("Image size: " + image.getSize());
                LOGGER.info("Image content type: " + image.getContentType());
                imageService.validateImage(image);
                String imageName = ad.getId() + ".jpg";
                imageService.saveImage(imageName, image);
                Image img = new Image();
                img.setImg(imageName);
                img.setAdId(ad.getId());
                img.setMain(true);
                imageDao.create(img);
                LOGGER.info("Новое фото объявления: {}", img);
            }
        } catch (ImageUploadException e) {
            bindingResult.reject(e.getMessage());
            return "ad";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAd(@PathVariable int id, Model model) {
        LOGGER.info("mapping get /edit/" + id);
        Ad ad = adDao.selectById(id);
        model.addAttribute("categories", categoryDao.getAll());
        model.addAttribute("ad", ad);
        model.addAttribute("selected", ad.getCategoryId());
        model.addAttribute("submit", "Сохранить изменения");
        return "ad";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateAd(@PathVariable int id, @ModelAttribute("ad") Ad ad, MultipartFile image,
                           BindingResult bindingResult) {
        LOGGER.info("mapping post /edit/" + id);
        Ad newAd = adDao.selectById(id);
        newAd.setTitle(ad.getTitle());
        newAd.setText(ad.getText());
        newAd.setCategoryId(ad.getCategoryId());
        newAd.setPrice(ad.getPrice());
        newAd.setPriceMin(ad.getPriceMin());
        adDao.update(newAd);
        LOGGER.info("объявление изменено: {}", newAd);
        LOGGER.info("image.isEmpty(): " + image.isEmpty());
        try {
            if (!image.isEmpty()) {
                LOGGER.info("Image size: " + image.getSize());
                LOGGER.info("Image content type: " + image.getContentType());
                imageService.validateImage(image);
                String imageName = id + ".jpg";
                imageService.saveImage(imageName, image);
                if (!imageDao.exists(id)) {
                    Image img = new Image();
                    img.setImg(imageName);
                    img.setAdId(id);
                    img.setMain(true);
                    imageDao.create(img);
                    LOGGER.info("Новое фото объявления: {}", img);
                }
            }
        } catch (ImageUploadException e) {
            bindingResult.reject(e.getMessage());
            return "ad";
        }
        return "redirect:/";
    }
}
