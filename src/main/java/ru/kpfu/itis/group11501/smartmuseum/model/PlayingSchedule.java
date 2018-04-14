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

    public PlayingSchedule(Date beginTime, Date endTime, WeekDay week_day, Projector projector) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.week_day = week_day;
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
    private WeekDay week_day;


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

    public WeekDay getWeek_day() {
        return week_day;
    }

    public void setWeek_day(WeekDay week_day) {
        this.week_day = week_day;
    }

    public Projector getProjector() {
        return projector;
    }

    public void setProjector(Projector projector) {
        this.projector = projector;
    }
}