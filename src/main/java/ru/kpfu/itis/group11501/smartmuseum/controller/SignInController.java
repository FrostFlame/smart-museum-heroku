package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.group11501.smartmuseum.util.AuthForm;

/**
 * Created by Bogdan Popov on 05.11.2017.
 */
@Controller
public class SignInController {

    @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
    public String getSignIn(Model model) {
        model.addAttribute("authForm", new AuthForm());
        return "sign_in";
    }

}
