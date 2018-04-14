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


    public Projector(String name, char status, Video currentVideo, Long sumTime, Date videoTime, List<Video> videos) {
        this.name = name;
        this.status = status;
        this.currentVideo = currentVideo;
        this.sumTime = sumTime;
        this.videoTime = videoTime;
        this.videos = videos;
    }

    public Projector() {
    }

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private char status;

    @ManyToOne(optional = false)
    private Video currentVideo;



    @Column(nullable = false, name = "sum_time")
    private Long sumTime;

    @Column(nullable = false, name = "video_time")
    @Temporal(TemporalType.TIME)
    private Date videoTime;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable( name = "projector_videos",
            joinColumns = {@JoinColumn(name = "projectorsid")},
            inverseJoinColumns = {@JoinColumn(name = "videosid")}
    )
    private List<Video> videos;

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

    public Video getCurrentVideo() {
        return currentVideo;
    }

    public void setCurrentVideo(Video currentVideo) {
        this.currentVideo = currentVideo;
    }

    public Long getSumTime() {
        return sumTime;
    }

    public void setSumTime(Long sumTime) {
        this.sumTime = sumTime;
    }

    public Date getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Date videoTime) {
        this.videoTime = videoTime;
    }

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }
}