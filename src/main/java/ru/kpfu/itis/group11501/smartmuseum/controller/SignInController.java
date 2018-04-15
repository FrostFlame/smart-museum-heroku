package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.kpfu.itis.group11501.smartmuseum.model.Position;
import ru.kpfu.itis.group11501.smartmuseum.model.Role;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.repository.PositionRepository;
import ru.kpfu.itis.group11501.smartmuseum.repository.RoleRepository;
import ru.kpfu.itis.group11501.smartmuseum.repository.UserRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;

import ru.kpfu.itis.group11501.smartmuseum.util.AuthForm;

import java.util.Date;

/**
 * Created by Bogdan Popov on 05.11.2017.
 */
@Controller
public class SignInController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PositionRepository positionRepository;

    @Autowired
    public SignInController(UserRepository userRepository, RoleRepository roleRepository, PositionRepository positionRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.positionRepository = positionRepository;
    }

    @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
    public String getSignIn(Model model) {
        model.addAttribute("authForm", new AuthForm());
        return "sign_in";
    }

    @RequestMapping(value = "/create_user")
    public String createUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Role role = roleRepository.getOne(2L);
        String password = encoder.encode("manager");
        Position position = positionRepository.getOne(2L);
        System.out.println(password);
        User u = new User(password, "manager", "Вова", "Иванов", "", "", true, role, position, new Date());
        userRepository.save(u);
        return "sign_in";
    }

}
