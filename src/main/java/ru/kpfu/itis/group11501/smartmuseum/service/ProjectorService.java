package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.Action;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.CoherentEntity;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.ActionTypeName;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.EntityName;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
@CoherentEntity(name = EntityName.PROJECTOR)
public interface ProjectorService {
    Projector getOneById(Long projector_id);

    @Action(name = ActionTypeName.ADD)
    Projector add(Projector projector);

    List<Projector> getFreeProjectors();

    List<Projector> getAllProjectors();

    @Action(name = ActionTypeName.DELETE)
    void deleteProjector(Long id);

    void turnOn(Long id);

    void turnOff(Long id);

    @Action(name = ActionTypeName.FAULT)
    void projectorFault(Long id);
}
