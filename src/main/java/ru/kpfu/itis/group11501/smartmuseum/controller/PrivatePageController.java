package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.group11501.smartmuseum.model.User;

import java.security.Principal;

@Controller
@RequestMapping(path = "/private")
public class PrivatePageController {

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model, Authentication authentication){
        User user = (User)authentication.getPrincipal();
        model.addAttribute("user", user);
        return "private_page";
    }
}
