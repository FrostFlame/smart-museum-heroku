package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.group11501.smartmuseum.model.Position;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.model.UserDto;
import ru.kpfu.itis.group11501.smartmuseum.service.PositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;
import ru.kpfu.itis.group11501.smartmuseum.util.Helpers;

import javax.validation.Valid;
import java.security.Principal;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;


@Controller
public class UserController {
    private UserService userService;
    public PositionService positionService;

    @Autowired
    public UserController(UserService userService, PositionService positionService) {
        this.userService = userService;
        this.positionService = positionService;
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

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public String registration_get(Model model){
        UserDto userDto = new UserDto();
        model.addAttribute("u", userDto);
        model.addAttribute("positions", positionService.getAllPositions());
        return "registration";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount
            (@ModelAttribute("user") @Valid UserDto accountDto,
             BindingResult result, WebRequest request, Errors errors) throws NoSuchFieldException {
        User registered = new User();
        AbstractMap.SimpleEntry<User, String> pair = null;
        String passwd = "";
        if (!result.hasErrors()) {
            pair = createUserAccount(accountDto, result);
            registered = pair.getKey();
            passwd = pair.getValue();
        }
        if (registered == null) {
            result.rejectValue("login", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        }
        else {
            return new ModelAndView("password", "passwd", passwd);
        }
    }

    private AbstractMap.SimpleEntry<User, String> createUserAccount(UserDto accountDto, BindingResult result) {
        AbstractMap.SimpleEntry<User, String> registered = null;
//        try {
            registered = userService.registerNewUserAccount(accountDto);
//        } catch (EmailExistsException e) {
//            return null;
//        }
        return registered;
    }
}
