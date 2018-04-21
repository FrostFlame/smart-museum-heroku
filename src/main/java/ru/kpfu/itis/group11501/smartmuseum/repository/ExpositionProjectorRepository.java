package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.group11501.smartmuseum.model.Exposition;
import ru.kpfu.itis.group11501.smartmuseum.model.ExpositionProjector;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;

public interface ExpositionProjectorRepository  extends JpaRepository<ExpositionProjector, Long> {

    ExpositionProjector findByProjectorAndAndExposition(Projector projector, Exposition exposition);
}
