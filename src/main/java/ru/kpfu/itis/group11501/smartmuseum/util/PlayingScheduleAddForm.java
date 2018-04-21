package ru.kpfu.itis.group11501.smartmuseum.util;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;
import java.util.List;

/**
 * Created by volkov on 13.04.2018.
 */
public class PlayingScheduleAddForm {


    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME,pattern = "HH:mm")
    @NotNull
    private Date beginTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME,pattern = "HH:mm")
    @NotNull
    private Date endTime;

    @NotEmpty
    private List<String> weekDaysId;

    @NotEmpty
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
