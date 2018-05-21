package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;
import ru.kpfu.itis.group11501.smartmuseum.util.Helpers;

import java.security.Principal;
import java.util.Collection;


@Controller
public class PrivatePageController {
    private UserService userService;

    @Autowired
    public PrivatePageController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public String get(Model model) {
        User user = Helpers.getCurrentUser();
        model.addAttribute("u", user);
        return "private_page";
    }

    @RequestMapping(path = "/profile/{id}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "id") Long userId, Model model) {
        if (userId.equals(Helpers.getCurrentUser().getId())){
            return "forward:/profile";
        }
        User user = userService.getUser(userId);
        model.addAttribute("u", user);
        return "private_page";
    }
}
