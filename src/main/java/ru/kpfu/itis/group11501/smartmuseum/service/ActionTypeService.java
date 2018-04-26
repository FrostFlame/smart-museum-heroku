package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.ActionType;

import java.util.List;

/**
 * Created by volkov on 25.04.2018.
 */
public interface ActionTypeService {
    ActionType getOneByName(String name);

    List<ActionType> findAll();
}
