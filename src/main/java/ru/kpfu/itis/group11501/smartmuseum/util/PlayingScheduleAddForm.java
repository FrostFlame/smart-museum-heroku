package ru.kpfu.itis.group11501.smartmuseum.util;

import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by volkov on 13.04.2018.
 */

public class PlayingScheduleAddForm {

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME,pattern = "HH:mm")
    @NotNull(message = "Введите время")
    private Date beginTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME,pattern = "HH:mm")
    @NotNull(message = "Введите время")
    private Date endTime;

    @NotEmpty(message = "Выберите дни недели")
    private List<String> weekDaysId;

    @NotEmpty(message = "Выберите проектора")
    private List<String> projectorsId;

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<String> getWeekDaysId() {
        return weekDaysId;
    }

    public void setWeekDaysId(List<String> weekDaysId) {
        this.weekDaysId = weekDaysId;
    }

    public List<String> getProjectorsId() {
        return projectorsId;
    }

    public void setProjectorsId(List<String> projectorsId) {
        this.projectorsId = projectorsId;
    }
}
