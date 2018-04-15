package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Position;

import java.util.List;

public interface PositionService {
    List<Position> getAllPositions();

    Position getPosition(Long positionId);
}
