package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.util.Helpers;

import java.security.Principal;
import java.util.Collection;


@Controller
@RequestMapping(path = "/profile")
public class PrivatePageController {

    @RequestMapping(method = RequestMethod.GET)
    public String get(Model model){
        User user = Helpers.getCurrentUser();
        model.addAttribute("u", user);
        return "private_page";
    }
}
