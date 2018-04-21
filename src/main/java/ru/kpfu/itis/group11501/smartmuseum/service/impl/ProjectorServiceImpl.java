package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.repository.ProjectorRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorService;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
@Service
public class ProjectorServiceImpl implements ProjectorService {

    private ProjectorRepository projectorRepository;

    public ProjectorServiceImpl(ProjectorRepository projectorRepository) {
        this.projectorRepository = projectorRepository;
    }

    @Override
    public Projector getOneById(Long projectorId){
        return projectorRepository.findOne(projectorId);
    }

    @Override
    public List<Projector> getFreeProjectors() {
        return projectorRepository.findAllFree();
    }

    @Override
    public void add(Projector projector) {
        projectorRepository.save(projector);
    }

}
