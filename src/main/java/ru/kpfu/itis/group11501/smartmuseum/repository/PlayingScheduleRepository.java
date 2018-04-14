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
    @Query("select x from PlayingSchedule as x where x.projector.id in ?1 ")
    List<PlayingSchedule> getPlayingScheduleByExposition( List<Long> projectors_id);

    @Query("select x from PlayingSchedule as x where x.projector.id in ?1 and x.week_day in ?2 ")
    List<PlayingSchedule> getPlayingScheduleByProjectorsByWeekDay( List<Long> projectorsId, List<Long> weekDaysId);

}
