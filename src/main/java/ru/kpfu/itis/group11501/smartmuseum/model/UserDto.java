package ru.kpfu.itis.group11501.smartmuseum.model;

import org.hibernate.validator.constraints.NotEmpty;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.ValidEmail;

import javax.validation.constraints.NotNull;

public class UserDto {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String surName;

    @NotNull
    @NotEmpty
    private String thirdName;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String login;

    @NotNull
    @NotEmpty
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

    // standard getters and setters
}