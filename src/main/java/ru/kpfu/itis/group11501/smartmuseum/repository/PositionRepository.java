package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.smartmuseum.model.Position;

import java.util.List;

/**
 * Created by Bogdan Popov on 15.04.2018.
 */
public interface PositionRepository extends JpaRepository<Position, Long> {
    List<Position> findAll();
}
