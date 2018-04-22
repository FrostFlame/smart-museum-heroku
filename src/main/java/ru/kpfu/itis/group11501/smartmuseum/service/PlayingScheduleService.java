package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;

import java.util.Date;
import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
public interface PlayingScheduleService {


    List<PlayingSchedule> getAllPlayingSchedule();

    PlayingSchedule addPlayingSchedule(PlayingSchedule playingSchedule);

    void save(PlayingSchedule playingSchedule);

    void deleteAllBetween(PlayingSchedule playingSchedule);

    void delete(PlayingSchedule playingSchedule);

    PlayingSchedule getOneWhereBeginTimeBefore(PlayingSchedule playingSchedule);

    PlayingSchedule getOneWhereBeginTimeAfter(PlayingSchedule playingSchedule);

    void deleteById(Long playingScheduleId);


    List<PlayingSchedule> getPlayingScheduleByParameters(List<Long> projectorsId, List<Long> weekDaysId, String sort);

    void addPlayingScheduleByParameters(List<String> projectorsId, List<String> weekDaysId, Date beginTime, Date endTime);
}
