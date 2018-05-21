package ru.kpfu.itis.group11501.smartmuseum.util;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 * Created by volkov on 21.05.2018.
 */
public class ChangePasswordForm {

    private String currentPassword;

    @Length(min = 6, max = 32, message = "Неверная длина пароля")
    private String newPassword;

    private String newPasswordConf;

    public ChangePasswordForm() {
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPasswordConf() {
        return newPasswordConf;
    }

    public void setNewPasswordConf(String newPasswordConf) {
        this.newPasswordConf = newPasswordConf;
    }

}