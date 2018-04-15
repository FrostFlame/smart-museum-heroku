package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.Position;
import ru.kpfu.itis.group11501.smartmuseum.repository.PositionRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.PositionService;

import java.util.List;
@Service
public class PositionServiceImpl implements PositionService {
    private PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    @Override
    public Position getPosition(Long positionId) {
        return positionRepository.findOne(positionId);
    }
}
