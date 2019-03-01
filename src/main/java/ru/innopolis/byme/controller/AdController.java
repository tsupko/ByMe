package ru.innopolis.byme.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.innopolis.byme.entity.Ad;
import ru.innopolis.byme.entity.Image;
import ru.innopolis.byme.exception.ImageUploadException;
import ru.innopolis.byme.service.AdService;
import ru.innopolis.byme.service.CategoryService;
import ru.innopolis.byme.service.ImageService;

import java.security.Principal;

@Controller
@RequestMapping("/ad")
public class AdController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdController.class);

    private final CategoryService categoryService;
    private final ImageService imageService;
    private final AdService adService;

    public AdController(CategoryService categoryService, ImageService imageService, AdService adService) {
        this.categoryService = categoryService;
        this.imageService = imageService;
        this.adService = adService;
    }

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
                        BindingResult bindingResult, Principal principal) {
        String login = principal.getName();
        LOGGER.info("mapping post /ad/new, login: {}", login);
        adService.createAd(ad, login);
        LOGGER.info("imageFile.isEmpty(): " + imageFile.isEmpty());
        String imageName = "no_image.jpg";
        try {
            if (!imageFile.isEmpty()) {
                imageService.validateImageFile(imageFile);
                imageName = ad.getId() + ".jpg";
                imageService.saveImageFile(imageName, imageFile);
                imageService.createImgById(ad.getId(), imageName);
            } else {
                imageService.createImgById(ad.getId(), imageName);
            }
        } catch (ImageUploadException e) {
            bindingResult.reject(e.getMessage());
            return "ad";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editAd(@PathVariable int id, Model model, Principal principal) {
        LOGGER.info("mapping get /edit/" + id);
        Ad ad = adService.selectById(id);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("user", principal.getName());
        model.addAttribute("ad", ad);
        model.addAttribute("selected", ad.getCategoryId());
        model.addAttribute("submit", "Сохранить изменения");
        return "ad";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String updateAd(@PathVariable int id, @ModelAttribute("ad") Ad ad,
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewAd(@PathVariable int id, Model model) {
        LOGGER.info("mapping get /ad/" + id);
        Ad ad = adService.selectById(id);
        Image image = imageService.getImageByAd(id);
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("ad", ad);
        model.addAttribute("selected", ad.getCategoryId());
        model.addAttribute("image", image.getImg());
        return "ad_view";
    }
}
