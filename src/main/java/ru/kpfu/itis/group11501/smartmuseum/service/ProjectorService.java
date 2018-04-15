package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Projector;

/**
 * Created by volkov on 12.04.2018.
 */
public interface ProjectorService {
    Projector getOneById(Long projector_id);

    void add(Projector projector);
}
