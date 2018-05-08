package ru.kpfu.itis.group11501.smartmuseum.model;

import org.springframework.format.annotation.DateTimeFormat;
import ru.kpfu.itis.group11501.smartmuseum.model.interfaces.GettingId;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by volkov on 13.04.2018.
 */
@Entity
@Table(name = "users_statistics")
public class UserStatistic implements GettingId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "usersid", referencedColumnName = "id")
    private User user;

    @ManyToOne(optional = false , targetEntity = ActionType.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "action_typesid", referencedColumnName = "id")
    private ActionType actionType;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;

    @ManyToOne(optional = false, targetEntity = TableName.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "tablenamesid", referencedColumnName = "id")
    private TableName tableName;

    @Column(nullable = false, name = "tableid")
    private Long tableid;

    @Transient
    private String link;

    public UserStatistic() {
    }

    public UserStatistic(User user, ActionType actionType, TableName tableName, Date datetime, Long tableid) {
        this.user = user;
        this.actionType = actionType;
        this.tableName = tableName;
        this.datetime = datetime;
        this.tableid = tableid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public Long getTableid() {
        return tableid;
    }

    public void setTableid(Long tableid) {
        this.tableid = tableid;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
