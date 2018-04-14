package ru.kpfu.itis.group11501.smartmuseum.model;

import javax.persistence.*;

import java.util.Date;

/**
 * Created by volkov on 12.04.2018.
 */

@Entity
@Table(name = "playing_schedule")
public class PlayingSchedule {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public PlayingSchedule(Date beginTime, Date endTime, WeekDay weekDay, Projector projector) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.weekDay = weekDay;
        this.projector = projector;
    }

    public PlayingSchedule() {
    }

    @Column( nullable = false)
    @Temporal(TemporalType.TIME)
    private Date beginTime;

    @Column( nullable = false)
    @Temporal(TemporalType.TIME)
    private Date endTime;

    @ManyToOne(optional = false)
    private WeekDay weekDay;


    @ManyToOne(optional = false)
    private Projector projector;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public WeekDay getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(WeekDay weekDay) {
        this.weekDay = weekDay;
    }

    public Projector getProjector() {
        return projector;
    }

    public void setProjector(Projector projector) {
        this.projector = projector;
    }
}