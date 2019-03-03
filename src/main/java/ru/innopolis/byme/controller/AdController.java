package ru.innopolis.byme.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.byme.entity.Ad;
import ru.innopolis.byme.entity.City;
import ru.innopolis.byme.entity.Image;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.exception.ImageUploadException;
import ru.innopolis.byme.service.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/ad")
public class AdController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdController.class);

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private AdService adService;
    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String ad(Model model, Principal principal) {
        LOGGER.info("mapping get /ad/new");
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("user", principal.getName());
        model.addAttribute("ad", new Ad());
        model.addAttribute("submit", "Добавить объявление");
        return "ad";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String addAd(@ModelAttribute("ad") Ad ad, MultipartFile imageFile,
                        BindingResult bindingResult, Principal principal, Model model) {
        String login = principal.getName();
        LOGGER.info("mapping post /ad/new, login: {}", login);
        LOGGER.info("imageFile.isEmpty(): " + imageFile.isEmpty());
        try {
            if (!imageFile.isEmpty()) {
                imageService.validateImageFile(imageFile);
                adService.createAd(ad, login);
                String imageName = ad.getId() + ".jpg";
                imageService.saveImageFile(imageName, imageFile);
                imageService.createImgById(ad.getId(), imageName);
            } else {
                adService.createAd(ad, login);
            }
        } catch (ImageUploadException e) {
            bindingResult.reject(e.getMessage());
            model.addAttribute("categories", categoryService.getAll());
            model.addAttribute("submit", "Добавить объявление");
            model.addAttribute("selected", ad.getCategoryId());
            return "ad";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAd(@PathVariable int id, Model model, Principal principal) {
        LOGGER.info("mapping get /edit/" + id);
        User user = userService.selectByLogin(principal.getName());
        LOGGER.info(" user = {}", user);
        Ad ad = adService.selectById(id);
        LOGGER.info(" ad = {}", ad);
        if (user.getId() == ad.getUserId()) {
            Image image = imageService.getImageByAd(id);
            model.addAttribute("categories", categoryService.getAll());
            model.addAttribute("ad", ad);
            model.addAttribute("user", principal.getName());
            model.addAttribute("selected", ad.getCategoryId());
            model.addAttribute("image", image.getImg());
            model.addAttribute("submit", "Сохранить изменения");
            return "ad";
        } else {
            return "redirect:/account";
        }
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateAd(@PathVariable int id, @Valid Ad ad,
                           MultipartFile imageFile, BindingResult bindingResult) {
        LOGGER.info("mapping post /edit/" + id);
        adService.updateAd(id, ad);
        LOGGER.info("image.isEmpty(): " + imageFile.isEmpty());
        try {
            if (!imageFile.isEmpty()) {
                imageService.validateImageFile(imageFile);
                String imageName = id + ".jpg";
                imageService.saveImageFile(imageName, imageFile);
                if (!imageService.exists(id)) {
                    imageService.createImgById(id, imageName);
                }
            }
        } catch (ImageUploadException e) {
            bindingResult.reject(e.getMessage());
            return "ad";
        }
        return "redirect:/account";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteAd(@PathVariable int id) {
        Ad ad = adService.selectById(id);
        adService.delete(ad);
        return "redirect:/account";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewAd(@PathVariable int id, Model model) {
        LOGGER.info("mapping get /ad/" + id);
        Ad ad = adService.selectById(id);
        Image image = imageService.getImageByAd(id);
        User user = userService.selectById(ad.getUserId());
        City city = cityService.selectByUser(user);
        LOGGER.info("user: {}", user);
        model.addAttribute("category", categoryService.getCategory(ad.getCategoryId()));
        model.addAttribute("ad", ad);
        model.addAttribute("image", image.getImg());
        model.addAttribute("seller", user);
        model.addAttribute("city", city);
        return "ad_view";
    }
}
