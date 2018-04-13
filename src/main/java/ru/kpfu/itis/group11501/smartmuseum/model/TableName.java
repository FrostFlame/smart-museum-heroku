package ru.kpfu.itis.group11501.smartmuseum.model;

import javax.persistence.*;

/**
 * Created by volkov on 13.04.2018.
 */
@Entity
@Table(name = "tablenames")
public class TableName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String name;

    public TableName() {
    }

    public TableName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
