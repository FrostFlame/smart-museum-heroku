package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;

/**
 * Created by volkov on 12.04.2018.
 */
public interface ProjectorRepository  extends JpaRepository<Projector, Long> {
}
