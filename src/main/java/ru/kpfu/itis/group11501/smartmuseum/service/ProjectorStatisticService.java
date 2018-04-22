package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.ProjectorStatistic;

import java.util.List;

/**
 * Created by Bogdan Popov on 22.04.2018.
 */
public interface ProjectorStatisticService {

        List<ProjectorStatistic> getAllStatistic(Projector projector);
}
