package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.kpfu.itis.group11501.smartmuseum.model.Role;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.repository.RoleRepository;
import ru.kpfu.itis.group11501.smartmuseum.repository.UserRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;

import ru.kpfu.itis.group11501.smartmuseum.util.AuthForm;

/**
 * Created by Bogdan Popov on 05.11.2017.
 */
@Controller
public class SignInController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public SignInController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
    public String getSignIn(Model model) {
        model.addAttribute("authForm", new AuthForm());
        return "sign_in";
    }

    @RequestMapping(value = "/create_user")
    public String createUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        //User u = new User(encoder.encode("1234"), "kolya", "kolya", "volkov", "", "", true, 1L, 2L, null);
        //userService.addUser(u);
        return "sign_in";
    }

}
