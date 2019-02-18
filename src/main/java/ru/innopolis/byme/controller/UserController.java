package ru.innopolis.byme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innopolis.byme.dao.UserDao;
import ru.innopolis.byme.entity.User;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private PasswordEncoder encoder;
    private final UserDao dao;

    @Autowired
    public UserController(UserDao dao, PasswordEncoder encoder) {
        this.dao = dao;
        this.encoder = encoder;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        LOGGER.info("Открыта страница регистрации");
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {

        user.setPassword(encoder.encode(user.getPassword()));

        LOGGER.info("Новый пользователь: {}", user);
        dao.create(user);
        return "redirect:/";
    }
}
