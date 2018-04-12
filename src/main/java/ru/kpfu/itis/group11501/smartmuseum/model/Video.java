package ru.kpfu.itis.group11501.smartmuseum.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by volkov on 12.04.2018.
 */
@Entity
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Video(String password, String login, String name, String surname, String third_name, String photo, boolean status, Long rolesid, Long positionsid, Date block_date) {

    }

    public Video() {
    }

    @Column( nullable = false)
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
}