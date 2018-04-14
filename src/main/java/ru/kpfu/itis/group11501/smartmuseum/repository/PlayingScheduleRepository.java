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
    @Query("select x from PlayingSchedule as x where x.projector.id in ?1 order by x.weekDay asc")
    List<PlayingSchedule> getPlayingScheduleByExposition( List<Long> projectors_id);

    @Query("select x from PlayingSchedule as x where x.projector.id in ?1 and x.weekDay.id in ?2 ")
    List<PlayingSchedule> getPlayingScheduleByProjectorsByWeekDay( List<Long> projectors_id, List<Long> weekDays_id);

    @Query("delete from PlayingSchedule as x where x.projector.id = ?1.projector.id and x.weekDay.id = ?1.weekDay.id and x.beginTime >= ?1.beginTime and x.endTime <= ?1.endTime")
    void deleteAllBetween(PlayingSchedule playingSchedule);

    @Query("select x from PlayingSchedule as x where x.projector.id = ?1.projector.id and x.weekDay.id = ?1.weekDay.id and x.beginTime < ?1.beginTime order by x.beginTime desc limit 1")
    PlayingSchedule getOneWhereBeginTimeBefore(PlayingSchedule playingSchedule);

    @Query("select x from PlayingSchedule as x where x.projector.id = ?1.projector.id and x.weekDay.id = ?1.weekDay.id and x.beginTime > ?1.beginTime order by x.beginTime asc limit 1")
    PlayingSchedule getOneWhereBeginTimeAfter(PlayingSchedule playingSchedule);
}
