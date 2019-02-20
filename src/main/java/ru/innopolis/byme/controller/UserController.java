package ru.innopolis.byme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.byme.dao.UserDao;
import ru.innopolis.byme.entity.User;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final PasswordEncoder encoder;
    private final UserDao dao;

    public UserController(UserDao dao, PasswordEncoder encoder) {
        LOGGER.info("создали userController");
        this.dao = dao;
        this.encoder = encoder;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        LOGGER.info("registration обработан userController get");
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") User user) {
        LOGGER.info("registration обработан userController post");
        user.setPassword(encoder.encode(user.getPassword()));
        LOGGER.info("Новый пользователь: {}", user);
        dao.create(user);
        return "redirect:/authorization";
    }

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String authorization(Model model){
        model.addAttribute(new User());
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

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home(){
        LOGGER.info("home обработан userController get");
        return "home";
    }

}
