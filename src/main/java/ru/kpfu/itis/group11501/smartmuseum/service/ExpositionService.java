package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Exposition;

/**
 * Created by volkov on 13.04.2018.
 */
public interface ExpositionService {
    Exposition getExpositionById(Long id);
}
