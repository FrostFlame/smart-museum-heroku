package ru.kpfu.itis.group11501.smartmuseum.util;


import javax.validation.constraints.NotNull;

/**
 * Created by volkov on 21.05.2018.
 */
public class AdminEditProfileForm extends EditProfileForm {

    @NotNull(message = "Выберите должность")
    private Long position;

    @NotNull(message = "Выберите роль")
    private Long role;

    public AdminEditProfileForm(Long id, String login, String name, String surname, String thirdName, String photo, Long position, Long role) {
        super(id, login, name, surname, thirdName, photo);
        this.position = position;
        this.role = role;
    }

    public AdminEditProfileForm() {
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
}
