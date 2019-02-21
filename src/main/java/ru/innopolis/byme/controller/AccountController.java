package ru.innopolis.byme.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String showUserAccount(){
        return "/account";
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public String changeSome(Model model){
        return "/account";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String getContact(){
        return "contact";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String postContact(Model model){
        return "redirect:/";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String getAboutUs(){
        return "about";
    }
}
