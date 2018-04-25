package ru.kpfu.itis.group11501.smartmuseum.model.enums;

/**
 * Created by volkov on 25.04.2018.
 */
public enum ActionTypeName {
    ADD("Добавление", "add"),
    DELETE("Удаление", "delete"),
    UPDATE("Изменение", "update"),
    TURNON("Включение", "enable"),
    TURNOFF("Выключение", "disable"),
    FAULT("Неисправен", "fault");

    private final String rusName;
    private final String engName;

    ActionTypeName(String rusName, String engName) {
        this.rusName = rusName;
        this.engName = engName;
    }

    public String getRusName() {
        return rusName;
    }

    public String getEngName() {
        return engName;
    }
}
