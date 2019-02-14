package ru.innopolis.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.project.dao.UserDAO;
import ru.innopolis.project.entity.User;

@Controller
public class UserController {
    private UserDAO dao;

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("form", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("form") User user) {
        dao.create(user);
        return "redirect:/";
    }
}
