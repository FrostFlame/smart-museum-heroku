package ru.kpfu.itis.group11501.smartmuseum.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.repository.RoleRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.PositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.RoleService;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;
import ru.kpfu.itis.group11501.smartmuseum.util.UserBlockForm;

import javax.validation.Valid;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("userBlockForm")
@RequestMapping(path = "/admin/users")
public class SearchUsersController {

    private UserService userService;
    private RoleService roleService;
    private PositionService positionService;

    @Autowired
    public SearchUsersController(UserService userService, RoleService roleService, PositionService positionService) {
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

}
