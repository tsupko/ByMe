package ru.innopolis.byme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.byme.entity.Ad;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.service.CityService;
import ru.innopolis.byme.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
    private static final int MAX_ADVERT_NUMBER = 20;

    @Autowired
    private UserService userService;
    @Autowired
    private CityService cityService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        LOGGER.info("registration обработан userController get");
        model.addAttribute("user", userService.newUser());
        model.addAttribute("cities", cityService.getAll());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, Model model) {
        LOGGER.info("registration обработан userController POST");
        try {
            userService.saveUser(user);
            LOGGER.info("добавление USER в БД");
        } catch (Exception e) {
            LOGGER.info("пользователь уже зарегистрирован");
            model.addAttribute("error", "user email reserved by other user");
            return "redirect:/registration";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, Principal principal) {

        LOGGER.info("index обработан userController get");

        List<Ad> advs = userService.getAdvs(MAX_ADVERT_NUMBER);
        model.addAttribute("list", advs);
        model.addAttribute("cityList", userService.getCityList());
        model.addAttribute("categoryList", userService.getCategoryList());

        if (principal == null) {
            model.addAttribute("logUrl", "/login");
            model.addAttribute("logStatus", "Log In");
        } else {
            model.addAttribute("logUrl", "/logout");
            model.addAttribute("logStatus", "Log Out");
            model.addAttribute("user", principal.getName());
        }
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String index(@RequestParam String cityId,
                        @RequestParam String categoryId) {
        System.err.println(cityId);
        System.err.println(categoryId);
        return "redirect:/";
    }

    @GetMapping("login")
    public String login(Principal principal) {
        if (principal == null) {
            return "login";
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String getAboutUs(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("logUrl", "/login");
            model.addAttribute("logStatus", "Log In");
        } else {
            model.addAttribute("logUrl", "/logout");
            model.addAttribute("logStatus", "Log Out");
            model.addAttribute("user", principal.getName());
        }
        return "about";
    }
}
