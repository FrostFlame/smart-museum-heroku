package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Projector;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
public interface ProjectorService {
    Projector getOneById(Long projector_id);

    List<Projector> getFreeProjectors();
}
