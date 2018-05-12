package ru.kpfu.itis.group11501.smartmuseum.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;
import ru.kpfu.itis.group11501.smartmuseum.repository.Specification.PlayingScheduleSpecification;

import java.util.Date;
import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
public interface PlayingScheduleRepository extends JpaRepository<PlayingSchedule, Long>, JpaSpecificationExecutor<PlayingSchedule> {

    @Transactional
    @Modifying
    @Query("delete from PlayingSchedule as x " +
            "where x.projector.id = ?1 and x.weekDay.id = ?2 and x.beginTime >= ?3 and x.endTime <= ?4")
    void deleteAllBetween(Long projectorId, Long weekDayId, Date beginTime, Date endTime);

    @Query("select p from PlayingSchedule as p " +
            "where p.beginTime = (select max(x.beginTime) from PlayingSchedule as x where x.projector.id = ?1 and x.weekDay.id = ?2 and x.beginTime < ?3 ) " +
            "and p.projector.id = ?1 and p.weekDay.id = ?2")
    PlayingSchedule getOneWhereBeginTimeBefore(Long projectorId, Long weekDayId, Date beginTime);

    @Query("select p from PlayingSchedule as p " +
            "where p.beginTime = (select min(x.beginTime) from PlayingSchedule as x where x.projector.id = ?1 and x.weekDay.id = ?2 and x.beginTime > ?3) " +
            "and p.projector.id = ?1 and p.weekDay.id = ?2")
    PlayingSchedule getOneWhereBeginTimeAfter(Long projectorId, Long weekDayId, Date beginTime);

    @Transactional
    @Modifying
    void deleteById(Long playingScheduleId);

    default List<PlayingSchedule> getPlayingScheduleByProjectorsByWeekDay(List<Long> projectorsId, List<Long> weekDaysId, Pageable pageRequest) {
        return (findAll(PlayingScheduleSpecification.allByProjectorsByWeekDays(projectorsId, weekDaysId), pageRequest)).getContent();
    }

    default Long getCountRow(List<Long> projectorsId, List<Long> weekDaysId) {
        return (count(PlayingScheduleSpecification.allByProjectorsByWeekDays(projectorsId, weekDaysId)));
    }
}
