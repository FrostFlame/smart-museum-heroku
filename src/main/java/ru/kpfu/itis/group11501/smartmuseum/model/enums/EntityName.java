package ru.kpfu.itis.group11501.smartmuseum.model.enums;

/**
 * Created by volkov on 25.04.2018.
 */
public enum EntityName {
    EXPOSITION("Экспозиция", "exposition", "/expositions/"),
    PLAYINGSCHEDULE("Расписание", "playingSchedule", null), // not page for individual playing_schedule
    PROJECTORSVIDEOS("ПроеткорВидео", "projectorsVideos", null),  //not page for individual projector_video; only add
    VIDEO("Видео", "video", null),   //not page for individual video
    PROJECTOR("Проектор", "projector", "/projector/"), // need do update,turnnon,turnoff,fault
    USER("Пользователь", "user", "/user/");  //only add
    private final String rusName;
    private final String engName;
    private final String link;

    EntityName (String rusName, String engName, String link) {
        this.rusName = rusName;
        this.engName = engName;
        this.link = link;
    }

    public String getRusName() {
        return rusName;
    }

    public String getEngName() {
        return engName;
    }

    public String getLink() {
        return link;
    }
}
