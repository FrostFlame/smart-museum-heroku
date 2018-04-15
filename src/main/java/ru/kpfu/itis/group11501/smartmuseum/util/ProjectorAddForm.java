package ru.kpfu.itis.group11501.smartmuseum.util;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Bogdan Popov on 15.04.2018.
 */
public class ProjectorAddForm {

    @NotEmpty
    private String name;

    public ProjectorAddForm(String name) {
        this.name = name;
    }

    public ProjectorAddForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
