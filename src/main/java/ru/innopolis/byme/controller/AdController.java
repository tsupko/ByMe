package ru.innopolis.byme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.byme.dao.AdDao;
import ru.innopolis.byme.dao.CategoryDao;
import ru.innopolis.byme.dao.UserDao;
import ru.innopolis.byme.entity.Ad;
import ru.innopolis.byme.entity.User;


import java.security.Principal;

@Controller
public class AdController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdController.class);
    private final AdDao adDao;
    private final UserDao userDao;
    private final CategoryDao categoryDao;

    public AdController(AdDao adDao, UserDao userDao, CategoryDao categoryDao) {
        this.adDao = adDao;
        this.userDao = userDao;
        this.categoryDao = categoryDao;
    }

    @RequestMapping(value = "/ad", method = RequestMethod.GET)
    public String ad(Model model){
        LOGGER.info("mapping get /ad");
        model.addAttribute("categories", categoryDao.getAll());
        model.addAttribute("ad", new Ad());
        return "ad";
    }

    @RequestMapping(value = "/ad", method = RequestMethod.POST)
    public String addAd(@ModelAttribute("ad") Ad ad, Principal principal) {
        String login = principal.getName();
        LOGGER.info("mapping post /ad, login: {}", login);
        User user = userDao.selectByLogin(login).get();
        ad.setUserId(user.getId());
        ad.setConfirm(true);
        ad.setActual(true);
        LOGGER.info("Новое объявление: {}", ad);
        adDao.create(ad);
        return "redirect:/";
    }
}
