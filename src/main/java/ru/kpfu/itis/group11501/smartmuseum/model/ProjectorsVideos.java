package ru.kpfu.itis.group11501.smartmuseum.model;

import ru.kpfu.itis.group11501.smartmuseum.model.interfaces.GettingId;

import javax.persistence.*;

/**
 * Created by volkov on 20.04.2018.
 */
@Entity
@Table(name = "projectors_videos")
public class ProjectorsVideos implements GettingId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ProjectorsVideos(Video video, Projector projector, Long num) {
        this.video = video;
        this.projector = projector;
        this.num = num;
    }

    public ProjectorsVideos(Long id, Video video, Long num) {
        this.video = video;
        this.num = num;
        this.id = id;
    }

    public ProjectorsVideos() {
    }

    @ManyToOne(targetEntity = Projector.class)
    @JoinColumn(name = "projectorsid", referencedColumnName = "id")
    private Projector projector;


    @ManyToOne(targetEntity = Video.class)
    @JoinColumn(name = "videosid", referencedColumnName = "id")
    private Video video;

    @Column(nullable = false)
    private Long num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Projector getProjector() {
        return projector;
    }

    public void setProjector(Projector projector) {
        this.projector = projector;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
