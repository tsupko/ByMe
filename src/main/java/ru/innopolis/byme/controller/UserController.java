package ru.innopolis.byme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innopolis.byme.entity.Unity;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        LOGGER.info("registration обработан userController get");
        model.addAttribute("user", service.newUser());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user, Model model) {
        LOGGER.info("registration обработан userController POST");
        try {
            service.saveUser(user);
            LOGGER.info("добавление USER в БД");
        } catch (Exception e) {
            LOGGER.info("пользователь уже зарегистрирован");
            model.addAttribute("error", "notNull");
            return "registration";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, Principal principal){

        LOGGER.info("index обработан userController get");

        List<Unity> unity = service.getUnity();
        model.addAttribute("list", unity);

        if(principal == null){
            model.addAttribute("urlSome", "/login");
            model.addAttribute("some", "LogIn");
        } else{
            model.addAttribute("urlSome", "/logout");
            model.addAttribute("some", "LogOut");
            model.addAttribute("user", principal.getName());
        }
//        model.addAttribute("city", service.getCityList());
//        model.addAttribute("category", service.getCategoryList());
        return "index";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }
}
