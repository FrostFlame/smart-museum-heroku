package ru.kpfu.itis.group11501.smartmuseum.util;

import javafx.geometry.Pos;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.group11501.smartmuseum.model.Position;
import ru.kpfu.itis.group11501.smartmuseum.model.Role;

import java.io.File;

/**
 * Created by Amir Kadyrov
 * Date: 27.04.2018
 */
public class EditProfileForm {
    private Long id;
    private String login;
    private String name;
    private String surname;
    private String thirdName;
    private MultipartFile photoFile;
    private String photo;
    private Long position;
    private Long role;
    private String currentPassword;
    private String newPassword;
    private String newPasswordConf;

    public EditProfileForm() {
    }

    public EditProfileForm(Long id, String login, String name, String surname, String thirdName, String photo, Long position, Long role) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.thirdName = thirdName;
        this.photo = photo;
        this.position = position;
        this.role = role;
    }

    public EditProfileForm(Long id, String login, String name, String surname, String thirdName, String photo) {
        this.id = id;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.thirdName = thirdName;
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String password) {
        this.currentPassword = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConf() {
        return newPasswordConf;
    }

    public void setNewPasswordConf(String newPasswordConf) {
        this.newPasswordConf = newPasswordConf;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public MultipartFile getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(MultipartFile photoFile) {
        this.photoFile = photoFile;
    }
}
