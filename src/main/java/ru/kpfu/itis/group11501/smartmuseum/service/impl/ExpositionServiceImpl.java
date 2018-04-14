package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.Exposition;
import ru.kpfu.itis.group11501.smartmuseum.repository.ExpositionRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.ExpositionService;

import java.util.List;

/**
 * Created by volkov on 13.04.2018.
 */
@Service
public class ExpositionServiceImpl implements ExpositionService {

    private ExpositionRepository expositionRepository;


    public ExpositionServiceImpl(ExpositionRepository expositionRepository) {
        this.expositionRepository = expositionRepository;
    }

    @Override
    public Exposition getExpositionById(Long id){
        return expositionRepository.findOne(id);
    }

    @Override
    public Exposition getFirstExposition(){
        return expositionRepository.getFirstExposition();
    }

    @Override
    public List<Exposition> getAllExposition(){
        return expositionRepository.findAll();
    }
}
