package ru.innopolis.byme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.byme.entity.Ad;
import ru.innopolis.byme.entity.City;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.service.*;

import java.security.Principal;
import java.util.Collection;


@Controller
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AdService adService;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showUserAccount(Model model, Principal principal) {
        String login = principal.getName();
        User user = userService.selectByLogin(login);
        Collection<City> cities = userService.getCityList();
        City city = cityService.selectByUser(user);
        Collection<Ad> ads = adService.selectByUser(user);
        model.addAttribute("account", user);
        model.addAttribute("city", city);
        model.addAttribute("cities", cities);
        model.addAttribute("ads", ads);
        return "/account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String changeSome(@ModelAttribute("account") User user) {
        userService.update(user);
        return "redirect:/account";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String getContact() {
        return "contact";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String postContact() {
        return "redirect:/";
    }


}
