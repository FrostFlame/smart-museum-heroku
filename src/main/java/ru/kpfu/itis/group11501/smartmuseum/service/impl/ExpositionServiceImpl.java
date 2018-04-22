package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.Exposition;
import ru.kpfu.itis.group11501.smartmuseum.model.ExpositionProjector;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.repository.ExpositionProjectorRepository;
import ru.kpfu.itis.group11501.smartmuseum.repository.ExpositionRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.ExpositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by volkov on 13.04.2018.
 */
@Service
public class ExpositionServiceImpl implements ExpositionService {

    private ExpositionRepository expositionRepository;
    private ExpositionProjectorRepository expositionProjectorRepository;
    private ProjectorService projectorService;

    @Autowired
    public ExpositionServiceImpl(ExpositionRepository expositionRepository,
                                 ProjectorService projectorService,
                                 ExpositionProjectorRepository expositionProjectorRepository) {
        this.expositionRepository = expositionRepository;
        this.projectorService = projectorService;
        this.expositionProjectorRepository = expositionProjectorRepository;
    }

    @Override
    public Exposition getExpositionById(Long id) {
        return expositionRepository.findOne(id);
    }

    @Override
    public Exposition getFirstExposition() {
        return expositionRepository.getFirstExposition();
    }

    @Override
    public List<Exposition> getAllExposition() {
        return expositionRepository.findAll();
    }

    @Override
    public Exposition save(String name, List<String> projectorsId) {
        List<Projector> projectors = new ArrayList<>();
        for (String id : projectorsId) {
            projectors.add(projectorService.getOneById(Long.valueOf(id.trim())));
        }
        return expositionRepository.save(new Exposition(name, projectors));
    }

    @Override
    public void editExposition(Long id, String name, List<String> deleteProjectors, List<String> newProjectors) {
        Exposition exposition = expositionRepository.findOne(id);
        exposition.setName(name);
        List<Projector> projectors = exposition.getProjectors();
        List<Projector> willDelete = new ArrayList<>();
        if (deleteProjectors != null) {
            for (String projectorId : deleteProjectors) {
                for(Projector projector: projectors){
                    if (Long.valueOf(projectorId).equals(projector.getId())){
                        willDelete.add(projector);
                    }
                }
            }
        }
        projectors.removeAll(willDelete);
        if (newProjectors != null) {
            for (String projector : newProjectors) {
                projectors.add(projectorService.getOneById(Long.valueOf(projector)));
            }
        }
        System.out.println(projectors);
        exposition.setProjectors(projectors);
        expositionRepository.save(exposition);
    }

    @Override
    public void deleteExposition(Long id) {
        expositionRepository.delete(id);
    }

}
