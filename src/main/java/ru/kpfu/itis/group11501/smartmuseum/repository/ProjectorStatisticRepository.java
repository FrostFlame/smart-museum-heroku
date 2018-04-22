package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.ProjectorStatistic;

import java.util.List;

/**
 * Created by Bogdan Popov on 22.04.2018.
 */
public interface ProjectorStatisticRepository extends JpaRepository<ProjectorStatistic, Long> {
    List<ProjectorStatistic> findAllByProjector(Projector projector);
}
