package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.ProjectorStatistic;
import ru.kpfu.itis.group11501.smartmuseum.repository.ProjectorStatisticRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorStatisticService;

import java.util.Date;
import java.util.List;

/**
 * Created by Bogdan Popov on 22.04.2018.
 */
@Service
public class ProjectorStatisticServiceImpl implements ProjectorStatisticService {

    private ProjectorStatisticRepository projectorStatisticRepository;

    @Autowired
    public ProjectorStatisticServiceImpl(ProjectorStatisticRepository projectorStatisticRepository) {
        this.projectorStatisticRepository = projectorStatisticRepository;
    }

    @Override
    public List<ProjectorStatistic> getAllStatistic(Projector projector) {
        return projectorStatisticRepository.findAllByProjector(projector);
    }

    @Override
    public void addStatistic(Projector projector) {
        ProjectorStatistic projectorStatistic = new ProjectorStatistic();
        projectorStatistic.setBeginDate(new Date());
        projectorStatistic.setProjector(projector);
        projectorStatistic.setEndDate(null);
        projectorStatisticRepository.save(projectorStatistic);
    }

    @Override
    public ProjectorStatistic getLastStatistics(Projector projector) {
        return projectorStatisticRepository.getLastStatistics(projector);
    }

    @Override
    public Long addEndDate(Projector projector) {
        Date endDate = new Date();
        ProjectorStatistic projectorStatistic = getLastStatistics(projector);
        projectorStatistic.setEndDate(endDate);
        projectorStatisticRepository.save(projectorStatistic);
        return projector.getSumTime() + endDate.getTime() - projectorStatistic.getBeginDate().getTime();
    }
}
