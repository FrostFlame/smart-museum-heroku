package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.model.UserDto;
import ru.kpfu.itis.group11501.smartmuseum.service.PositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.RoleService;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;
import ru.kpfu.itis.group11501.smartmuseum.util.Helpers;
import ru.kpfu.itis.group11501.smartmuseum.util.UserBlockForm;

import javax.validation.Valid;
import java.util.*;

@Controller
@SessionAttributes("userBlockForm")
@RequestMapping(path = "/admin/users")
public class AdminController {

    private UserService userService;
    private RoleService roleService;
    private PositionService positionService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService, PositionService positionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.positionService = positionService;
    }

    @ModelAttribute("userBlockForm")
    public UserBlockForm getNewUserBlockForm(){return new UserBlockForm();}

    @RequestMapping(method = RequestMethod.GET)
    public String getSearchUsersPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("positions", positionService.getAllPositions());
        return "search_users";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchUsers(Model model, @RequestParam(name = "status") String status,
                              @RequestParam(name = "role") String roleId,
                              @RequestParam(name = "position") String positionId,
                              @RequestParam(name = "searchField") String searchField) {
        model.addAttribute("users", userService.getUsersByParameters(searchField, roleId, positionId, status));
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("positions", positionService.getAllPositions());
        return "search_users";
    }

    @RequestMapping(value = "/block", method = RequestMethod.POST)
    public String postBlockUser(Model model, @ModelAttribute("userBlockForm") @Valid UserBlockForm userBlockForm,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult);
            return "redirect:/admin/users/profile/" + userBlockForm.getUserID() + "/block";
        }
        userService.blockUser(userBlockForm.getUserID(), userBlockForm.getBlockDate());
        return "redirect:/admin/users";
    }

    @RequestMapping(value = "/{id}/unblock", method = RequestMethod.POST)
    public String postUnblockUser(@PathVariable Long id){
        userService.unblockUser(id);
        return "redirect:/admin/users";
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
             BindingResult result) throws NoSuchFieldException {
        User registered = new User();
        AbstractMap.SimpleEntry<User, String> pair;
        String passwd = "";
        if (!result.hasErrors()) {
            pair = createUserAccount(accountDto);
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

    @RequestMapping(path = "/profile/{id}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "id") Long userId, Model model) {
        if (userId.equals(Helpers.getCurrentUser().getId())){
            return "forward:/profile";
        }
        User user = userService.getUser(userId);
        model.addAttribute("u", user);
        return "private_page";
    }

    private AbstractMap.SimpleEntry<User, String> createUserAccount(UserDto accountDto) {
        AbstractMap.SimpleEntry<User, String> registered;
        registered = userService.registerNewUserAccount(accountDto);
        return registered;
    }

}
