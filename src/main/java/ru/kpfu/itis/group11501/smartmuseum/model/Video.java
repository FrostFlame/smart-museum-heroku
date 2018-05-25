package ru.kpfu.itis.group11501.smartmuseum.model;

import ru.kpfu.itis.group11501.smartmuseum.model.interfaces.GettingId;

import javax.persistence.*;
import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
@Entity
@Table(name = "videos")
public class Video implements GettingId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Video(String name) {
        this.name = name;
    }

    public Video() {
    }

    //need lazy
    @OneToMany(targetEntity = ProjectorsVideos.class,fetch = FetchType.EAGER,mappedBy = "projector")
    private List<Projector> projectors;

    @Column( nullable = false , unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Projector> getProjectors() {
        return projectors;
    }

    public void setProjectors(List<Projector> projectors) {
        this.projectors = projectors;
    }

}