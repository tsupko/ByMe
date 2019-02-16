package ru.innopolis.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innopolis.project.dao.UserDao;
import ru.innopolis.project.entity.User;

@Controller
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private UserDao dao;

    public UserController(UserDao dao) {
        this.dao = dao;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        LOGGER.info("Открыта страница регистрации");
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        LOGGER.info("Новый пользователь: {}", user);
        dao.create(user);
        return "redirect:/";
    }
}
