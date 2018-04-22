package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Exposition;

import java.util.List;

/**
 * Created by volkov on 13.04.2018.
 */
public interface ExpositionService {
    Exposition getExpositionById(Long id);

    Exposition getFirstExposition();

    List<Exposition> getAllExposition();

    Exposition save(String name, List<String> projectorsId);

    void editExposition(Long id, String name, List<String> deleteProjectors, List<String> newProjectors);

    void deleteExposition(Long id);
}
