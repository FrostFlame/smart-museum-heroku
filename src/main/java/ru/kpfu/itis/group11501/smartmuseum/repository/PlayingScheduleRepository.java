package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;

import java.util.Date;
import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
public interface PlayingScheduleRepository  extends JpaRepository<PlayingSchedule, Long> {
    @Query("select x from PlayingSchedule as x where x.projector.id in ?1 order by x.weekDay asc")
    List<PlayingSchedule> getPlayingScheduleByExposition( List<Long> projectors_id);

    @Query("select x from PlayingSchedule as x where x.projector.id in ?1 and x.weekDay.id in ?2 ")
    List<PlayingSchedule> getPlayingScheduleByProjectorsByWeekDay( List<Long> projectors_id, List<Long> weekDays_id);

    @Query("delete from PlayingSchedule as x where x.projector.id = ?1 and x.weekDay.id = ?2 and x.beginTime >= ?3 and x.endTime <= ?4")
    void deleteAllBetween(Long projectorId,Long weekDayId, Date beginTime,Date endTime);

    @Query("select p from PlayingSchedule as p where p.beginTime = (select max(x.beginTime) from PlayingSchedule as x where x.projector.id = ?1 and x.weekDay.id = ?2 and x.beginTime < ?3 )")
    PlayingSchedule getOneWhereBeginTimeBefore(Long projectorId,Long weekDayId, Date beginTime);

    @Query("select p from PlayingSchedule as p where p.beginTime = (select min(x.beginTime) from PlayingSchedule as x where x.projector.id = ?1 and x.weekDay.id = ?2 and x.beginTime > ?3 )")
    PlayingSchedule getOneWhereBeginTimeAfter(Long projectorId,Long weekDayId, Date beginTime);
}
