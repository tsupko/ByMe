package ru.innopolis.byme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.exception.UserLoginAlreadyExistsException;
import ru.innopolis.byme.service.UserService;

import java.security.Principal;

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
    public String registration(@ModelAttribute("user") User user) {
        LOGGER.info("registration обработан userController POST");
        try {
            service.saveUser(user);
            LOGGER.info("добавление USER в БД");
        } catch (UserLoginAlreadyExistsException e) {
            LOGGER.info("пользователь уже зарегистрирован");
            return "/registration";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, Principal principal){
        LOGGER.info("index обработан userController get");
        if(principal == null){
            model.addAttribute("urlSome", "/login");
            model.addAttribute("some", "LogIn");
        } else{
            model.addAttribute("urlSome", "/logout");
            model.addAttribute("some", "LogOut");
        }
        model.addAttribute("list", service.getImages());
        model.addAttribute("city", service.getCityList());
        model.addAttribute("category", service.getCategoryList());
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        LOGGER.info("home обработан userController get");
        return "home";
    }

    @GetMapping("login")
    public String login(){
        return "login";
    }
}
