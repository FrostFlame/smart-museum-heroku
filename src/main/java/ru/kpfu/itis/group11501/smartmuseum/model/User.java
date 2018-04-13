package ru.kpfu.itis.group11501.smartmuseum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Bogdan Popov on 26.03.2018.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User(String password, String login, String name, String surname, String third_name, String photo, boolean status, Role role, Position position, Date block_date) {
        this.password = password;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.third_name = third_name;
        this.photo = photo;
        this.status = status;
        this.role = role;
        this.position = position;
        this.block_date = block_date;
    }

    public User() {
    }

    @JsonIgnore
    private String password;
    @Column( nullable = false)
    private String login;
    @Column( nullable = false)
    private String name;
    @Column( nullable = false)
    private String surname;

    private String third_name;

    private String photo;

    @Column( nullable = false)
    private boolean status;

    @ManyToOne(optional = false)
    private Role role;

    @ManyToOne(optional = false)
    private Position position;

    private Date block_date;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getThird_name() {
        return third_name;
    }

    public void setThird_name(String third_name) {
        this.third_name = third_name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Role getRolesid() {
        return role;
    }

    public void setRolesid(Role role) {
        this.role = role;
    }

    public Position getPositionsid() {
        return position;
    }

    public void setPositionsid(Position position) {
        this.position = position;
    }

    public Date getBlock_date() {
        return block_date;
    }

    public void setBlock_date(Date block_date) {
        this.block_date = block_date;
    }
}
