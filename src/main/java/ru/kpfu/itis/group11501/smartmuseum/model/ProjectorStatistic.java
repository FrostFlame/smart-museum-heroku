package ru.kpfu.itis.group11501.smartmuseum.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Bogdan Popov on 22.04.2018.
 */
@Entity
@Table(name = "projectors_statistics")
public class ProjectorStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne(targetEntity = Projector.class)
    @JoinColumn(name = "projectorsid", referencedColumnName = "id")
    private Projector projector;

    public ProjectorStatistic() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Projector getProjector() {
        return projector;
    }

    public void setProjector(Projector projector) {
        this.projector = projector;
    }
}
