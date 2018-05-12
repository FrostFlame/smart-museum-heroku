package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.group11501.smartmuseum.repository.RoleRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.PositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.RoleService;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;
import ru.kpfu.itis.group11501.smartmuseum.util.UserBlockForm;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
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


    @RequestMapping(value = "/", method = RequestMethod.GET)
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

    @RequestMapping(value = "/profile/{id}/block", method = RequestMethod.GET)
    public String getBlockUser(Model model, @PathVariable("id") Long id){
        model.addAttribute("userBlockForm", new UserBlockForm(id));
        return "block_user";
    }

    @RequestMapping(value = "/profile/{id}/block", method = RequestMethod.POST)
    public String postBlockUser(Model model, @ModelAttribute("userBlockForm") @Valid UserBlockForm userBlockForm,
                                @PathVariable("id") Long id){
        int blockTime = userBlockForm.getBlockDate();
        return "block_user";
    }
}
