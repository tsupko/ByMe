package ru.innopolis.byme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.exception.UserLoginAlreadyExistsException;
import ru.innopolis.byme.service.UserService;

import javax.validation.Valid;

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
    public String registration(@ModelAttribute("user") @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "/registration";
        }
        try {
            service.saveUser(user);
        } catch (UserLoginAlreadyExistsException e) {
            LOGGER.info("пользователь уже зарегистрирован");
            return "/registration";
        }
        return "redirect:/authorization";
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String authorization(){
        LOGGER.info("authorization обработан userController get");
        return "authorization";
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public String authorization(@RequestParam String login, @RequestParam String password) {
        LOGGER.info("authorization обработан userController post");
        LOGGER.info("login {}", login);
        LOGGER.info("password {}", password);
        return "authorization";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        LOGGER.info("index обработан userController get");
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String index(@RequestParam String city, @RequestParam String category){
        LOGGER.info("index обработан userController post");
        LOGGER.info("city {}", city);
        LOGGER.info("category {}", category);
        return "index";
    }
}
