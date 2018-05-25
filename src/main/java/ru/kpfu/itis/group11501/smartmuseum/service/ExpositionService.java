package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Exposition;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.Action;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.CoherentEntity;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.ActionTypeName;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.EntityName;

import java.util.List;

/**
 * Created by volkov on 13.04.2018.
 */
@CoherentEntity(name = EntityName.EXPOSITION)
public interface ExpositionService {
    Exposition getExpositionById(Long id);

    Exposition getFirstExposition();

    List<Exposition> getAllExposition();

    @Action(name = ActionTypeName.ADD)
    Exposition save(String name, List<String> projectorsId);

    void editExposition(Long id, String name, List<String> deleteProjectors, List<String> newProjectors);

    @Action(name = ActionTypeName.UPDATE)
    void editExposition(Exposition exposition);

    @Action(name = ActionTypeName.DELETE)
    void deleteExposition(Long id);

    Exposition findOneByName(String name);
}
