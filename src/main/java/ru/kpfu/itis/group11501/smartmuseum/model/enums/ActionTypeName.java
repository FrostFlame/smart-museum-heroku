package ru.kpfu.itis.group11501.smartmuseum.model.enums;

/**
 * Created by volkov on 25.04.2018.
 */
public enum ActionTypeName {
    ADD("Добавление"),
    DELETE("Удаление"),
    UPDATE("Изменение"),
    TURNON("Включение"),
    TURNOFF("Выключение"),
    FAULT("Неисправен"),
    AUTHORIZATION("Авторизация");

    private final String rusName;

    ActionTypeName(String rusName) {
        this.rusName = rusName;
    }

    public String getRusName() {
        return rusName;
    }

}
