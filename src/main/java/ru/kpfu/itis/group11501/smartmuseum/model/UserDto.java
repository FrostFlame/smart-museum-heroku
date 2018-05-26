package ru.kpfu.itis.group11501.smartmuseum.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class UserDto {

    @NotBlank(message = "Введите Имя")
    private String name;

    @NotBlank(message = "Введите Фамилию")
    private String surName;

    private String thirdName;

    @NotBlank(message = "Введите Логин")
    private String login;

    @NotNull(message = "Выберите должность")
    private Long position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }

}