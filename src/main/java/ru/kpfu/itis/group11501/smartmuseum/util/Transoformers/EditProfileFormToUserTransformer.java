package ru.kpfu.itis.group11501.smartmuseum.util.Transoformers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.service.*;
import ru.kpfu.itis.group11501.smartmuseum.util.*;


import java.util.function.Function;

/**
 * Created by volkov on 21.05.2018.
 */
@Component
public class EditProfileFormToUserTransformer implements Function<EditProfileForm, User> {

    private UserService userService;
    private FileUploader fileUploader;
    private PositionService positionService;
    private RoleService roleService;

    @Autowired
    public EditProfileFormToUserTransformer(UserService userService, FileUploader fileUploader, PositionService positionService, RoleService roleService) {
        this.userService = userService;
        this.fileUploader = fileUploader;
        this.positionService = positionService;
        this.roleService = roleService;
    }

    private String firstUpperCase(String word) {
        if (word == null || word.isEmpty()) return "";
        return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
    }

    @Override
    public User apply(EditProfileForm editProfileForm) {
        User user = userService.getUser((editProfileForm.getId()));
        user.setName(firstUpperCase(editProfileForm.getName()));
        user.setSurname(firstUpperCase(editProfileForm.getSurname()));
        user.setThirdName(firstUpperCase(editProfileForm.getThirdName()));
        user.setLogin(editProfileForm.getLogin());
        String name = fileUploader.uploadImage(editProfileForm.getPhotoFile());
        if (name == null) {
            System.out.println("Не удалось добавить фото");
        } else {
            System.out.println(name);
            if (fileUploader.deleteImage(user.getPhoto()) == null) {
                System.out.println("Не удалось удалить фото");
            } else {
                System.out.println("Фото удалено");
            }
            user.setPhoto(name);
        }
        if (editProfileForm instanceof AdminEditProfileForm) {
            user.setPosition(positionService.getPosition(((AdminEditProfileForm) editProfileForm).getPosition()));
            user.setRole(roleService.getRole(((AdminEditProfileForm) editProfileForm).getRole()));
        }
        return user;
    }
}