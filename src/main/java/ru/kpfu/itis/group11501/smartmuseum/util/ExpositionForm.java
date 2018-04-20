package ru.kpfu.itis.group11501.smartmuseum.util;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

public class ExpositionForm {
    @Length(min = 1)
    private String name;

    @NotEmpty
    private List<String> projectorsId;

    public void setName(String name) {
        this.name = name;
    }

    public void setProjectorsId(List<String> projectorsId) {
        this.projectorsId = projectorsId;
    }

    public String getName() {
        return name;
    }

    public List<String> getProjectorsId() {
        return projectorsId;
    }
}
