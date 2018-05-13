package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.service.PositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.RoleService;
import ru.kpfu.itis.group11501.smartmuseum.service.UserService;
import ru.kpfu.itis.group11501.smartmuseum.util.EditProfileForm;
import ru.kpfu.itis.group11501.smartmuseum.util.FileUploader;
import ru.kpfu.itis.group11501.smartmuseum.util.Helpers;

import javax.validation.Valid;

/**
 * Created by Amir Kadyrov
 * Date: 27.04.2018
 */
@Controller
@SessionAttributes("editForm")
public class EditUserController {
    private UserService userService;
    private RoleService roleService;
    private PositionService positionService;


    @Autowired
    public EditUserController(UserService userService, RoleService roleService, PositionService positionService) {
        this.userService = userService;
        this.roleService = roleService;
        this.positionService = positionService;
    }

    @ModelAttribute("editForm")
    public EditProfileForm getNewEditForm() {
        return new EditProfileForm();
    }

    @RequestMapping(value = "/profile/{id}/edit", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdminEditProfile(Model model, @PathVariable(value = "id", required = true) Long id,
                                      @ModelAttribute("error") String error,
                                      @ModelAttribute("editForm") EditProfileForm editProfileForm) {
        User editableUser = userService.getUser(id);
        if (error != null && !error.equals("")) {
            model.addAttribute("error", error);
        }
        editProfileForm = new EditProfileForm(editableUser.getId(), editableUser.getLogin(), editableUser.getName(),
                editableUser.getSurname(), editableUser.getThirdName(), editableUser.getPhoto(),
                editableUser.getPosition().getId(), editableUser.getRole().getId());
        model.addAttribute("editForm", editProfileForm);
        model.addAttribute("positions", positionService.getAllPositions());
        model.addAttribute("roles", roleService.getAllRoles());
        model.addAttribute("u", Helpers.getCurrentUser());
        return "edit_profile";
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_NORMAL', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public String getNormalEditProfile(Model model, @ModelAttribute("error") String error,
                                       @ModelAttribute("editForm") EditProfileForm editProfileForm) {
        User editableUser = Helpers.getCurrentUser();
        if (editableUser.getRole().getName().equals("ADMIN")) {
            return "redirect:/profile/"+ editableUser.getId()+"/edit";
        }
        if (error != null && !error.equals("")) {
            model.addAttribute("error", error);
        }
        editProfileForm = new EditProfileForm(editableUser.getId(), editableUser.getLogin(), editableUser.getName(),
                editableUser.getSurname(), editableUser.getThirdName(), editableUser.getPhoto());
        model.addAttribute("editForm", editProfileForm);
        model.addAttribute("editProfileID", editableUser.getId());
        model.addAttribute("u", Helpers.getCurrentUser());
        return "edit_profile";
    }

    @RequestMapping(value = "/edit_profile", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_NORMAL', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    public String postEditProfile(Model model, @ModelAttribute("editForm") @Valid EditProfileForm editProfileForm,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", bindingResult);
            return "redirect:/profile/edit";
        }
        if (!editProfileForm.getNewPassword().equals(editProfileForm.getNewPasswordConf()) &&
                !editProfileForm.getNewPassword().equals("")) {
            redirectAttributes.addFlashAttribute("error", "Введите верное подтверждение нового пароля.");
            return "redirect:/profile/edit";
        }
        if (Helpers.getCurrentUser().getRole().getName().equals("ADMIN")) {
            userService.adminEditProfileAndSave(userService.getUser(editProfileForm.getId()), editProfileForm);
            if (Helpers.getCurrentUser().getId().equals( editProfileForm.getId())){
                userService.updateCurrentSession(editProfileForm.getId());
            }
            // TODO redirect to existing URL
            return "redirect:/admin/users";
        }
        User editableUser = Helpers.getCurrentUser();
        if (!Helpers.getEncoder().matches(editProfileForm.getCurrentPassword(), editableUser.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "Неверный текущий пароль.");
            return "redirect:/profile/edit";
        }
        userService.normalEditProfileAndSave(editableUser, editProfileForm);
        userService.updateCurrentSession(editProfileForm.getId());
        return "redirect:/profile";
    }

}
