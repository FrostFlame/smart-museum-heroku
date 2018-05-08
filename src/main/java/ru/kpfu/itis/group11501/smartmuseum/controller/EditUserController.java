package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.group11501.smartmuseum.model.Position;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.service.PositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.RoleService;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;
import ru.kpfu.itis.group11501.smartmuseum.util.EditProfileForm;
import ru.kpfu.itis.group11501.smartmuseum.util.Helpers;

/**
 * Created by Amir Kadyrov
 * Date: 27.04.2018
 */
@Controller
public class EditUserController {
    private UserService userService;
    private EditProfileForm editProfileForm;
    private RoleService roleService;
    private PositionService positionService;

    @Autowired
    public EditUserController(UserService userService, RoleService roleService, PositionService positionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.positionService = positionService;
    }

    @RequestMapping(value = "/user/{id}/edit", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdminEditProfile(Model model, @PathVariable(value = "id") Long id, @ModelAttribute String error) {
        User editableUser = userService.getUser(id);
        editProfileForm = new EditProfileForm(editableUser.getLogin(), editableUser.getName(),
                editableUser.getSurname(), editableUser.getThirdName(), editableUser.getPhoto());
        model.addAttribute("editForm", editProfileForm);
        model.addAttribute("positions", positionService.getAllPositions());
        model.addAttribute("roles", roleService.getAllRoles());
        return "edit_profile";
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_NORMAL', 'ROLE_MANAGER')")
    public String getNormalEditProfile(Model model, @ModelAttribute String error) {
        User editableUser = Helpers.getCurrentUser();
        EditProfileForm editProfileForm = new EditProfileForm(editableUser.getLogin(), editableUser.getName(),
                editableUser.getSurname(), editableUser.getThirdName(), editableUser.getPhoto());
        model.addAttribute("editForm", editProfileForm);
        return "edit_profile";
    }

    @RequestMapping(value = "/edit_profile", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_NORMAL', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public String postNormalEditProfile(Model model, @ModelAttribute EditProfileForm editProfileForm,
                                        BindingResult bindingResult,
                                        @ModelAttribute String error) {
        if ((error != null && !error.equals("")) || bindingResult.hasErrors()) {
            //need redirect, look playingSchedule method add
            return "404_not_found";
        }

        if (Helpers.getCurrentUser().getRole().getName().equals("ADMIN")){
            //editableUser need receive from view , this is kostyl
            userService.adminEditProfile(userService.getUser(1L), editProfileForm);
            //need redirect, look playingSchedule method add
            return "404_not_found";
        }
        User editableUser = Helpers.getCurrentUser();
        if (!Helpers.getEncoder().matches(editProfileForm.getCurrentPassword(), editableUser.getPassword())) {
            model.addAttribute("error", "Неверный текущий пароль.");
            //need redirect, look playingSchedule method add
            return "404_not_found";
        }
        userService.normalEditProfile(editableUser, editProfileForm);
        //need redirect, look playingSchedule method add
        return "404_not_found";
    }

}
