package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
public interface PlayingScheduleRepository  extends JpaRepository<PlayingSchedule, Long> {
    @Query("select x from PlayingSchedule as x where x.projector in ?1 ")
    List<PlayingSchedule> getPlayingScheduleByProjectors( List<Projector> projectors);
}
