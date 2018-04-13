package ru.kpfu.itis.group11501.smartmuseum.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */


@Entity
@Table(name = "projectors")
public class Projector {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Projector(String name, char status, Video current_video, Long sum_time, Date video_time, List<Video> videos) {
        this.name = name;
        this.status = status;
        this.current_video = current_video;
        this.sum_time = sum_time;
        this.video_time = video_time;
        this.videos = videos;
    }

    public Projector() {
    }

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    public char status;

    @ManyToOne(optional = false)
    public Video current_video;



    @Column(nullable = false)
    public Long sum_time;

    @Column( nullable = false)
    @Temporal(TemporalType.TIME)
    public Date video_time;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( name = "projector_videos",
            joinColumns = {@JoinColumn(name = "projectorsid")},
            inverseJoinColumns = {@JoinColumn(name = "videosid")}
    )
    public List<Video> videos;

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

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public Video getCurrent_video() {
        return current_video;
    }

    public void setCurrent_video(Video current_video) {
        this.current_video = current_video;
    }

    public Long getSum_time() {
        return sum_time;
    }

    public void setSum_time(Long sum_time) {
        this.sum_time = sum_time;
    }

    public Date getVideo_time() {
        return video_time;
    }

    public void setVideo_time(Date video_time) {
        this.video_time = video_time;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}