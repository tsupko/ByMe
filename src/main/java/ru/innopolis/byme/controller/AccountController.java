package ru.innopolis.byme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.byme.dao.AdDao;
import ru.innopolis.byme.dao.CityDao;
import ru.innopolis.byme.dao.UserDao;
import ru.innopolis.byme.entity.Ad;
import ru.innopolis.byme.entity.City;
import ru.innopolis.byme.entity.User;

import java.security.Principal;
import java.util.Collection;


@Controller
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    private final UserDao userDao;
    private final CityDao cityDao;
    private final AdDao adDao;

    public AccountController(UserDao userDao, CityDao cityDao, AdDao adDao) {
        this.userDao = userDao;
        this.cityDao = cityDao;
        this.adDao = adDao;
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showUserAccount(Model model, Principal principal) {
        String login = principal.getName();
        User user = userDao.selectByLogin(login).get();
        Collection<City> cities = cityDao.getAllCities();
        City city = cityDao.selectById(user.getCityId()).get();
        Collection<Ad> ads = adDao.selectByUserId(user.getId());
        model.addAttribute("account", user);
        model.addAttribute("city", city);
        model.addAttribute("cities", cities);
        model.addAttribute("ads", ads);
        return "/account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String changeSome(@ModelAttribute("account") User user) {
        userDao.update(user);
        return "/account";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String getContact() {
        return "contact";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String postContact(Model model) {
        return "redirect:/";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String getAboutUs() {
        return "about";
    }
}