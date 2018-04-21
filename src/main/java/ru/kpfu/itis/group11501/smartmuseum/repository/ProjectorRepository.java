package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
public interface ProjectorRepository  extends JpaRepository<Projector, Long> {
    @Query("select p from Projector p where NOT EXISTS(select exp_p FROM ExpositionProjector exp_p " +
            "where p.id = exp_p.projector.id)")
    List<Projector> findAllFree();
}
