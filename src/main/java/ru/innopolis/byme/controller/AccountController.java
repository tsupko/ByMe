package ru.innopolis.byme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innopolis.byme.entity.Ad;
import ru.innopolis.byme.entity.City;
import ru.innopolis.byme.entity.User;
import ru.innopolis.byme.exception.UserLoginAlreadyExistsException;
import ru.innopolis.byme.service.AdService;
import ru.innopolis.byme.service.CityService;
import ru.innopolis.byme.service.MainService;

import java.security.Principal;
import java.util.Collection;


@Controller
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private MainService mainService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AdService adService;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showUserAccount(Model model, Principal principal) {
        String login = principal.getName();
        User user = mainService.selectByLogin(login);
        Collection<City> cities = cityService.getCityList();
        City city = cityService.selectByUser(user);
        Collection<Ad> ads = adService.selectByUser(user);
        model.addAttribute("account", user);
        model.addAttribute("city", city);
        model.addAttribute("cities", cities);
        model.addAttribute("ads", ads);
        return "/account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String changeSome(@ModelAttribute("account") User user, Model model) {
        try {
            mainService.update(user);
        } catch (UserLoginAlreadyExistsException e){
            model.addAttribute("error", e.getMessage());
            return "redirect:/account";
        }
        return "redirect:/account";
    }

}
