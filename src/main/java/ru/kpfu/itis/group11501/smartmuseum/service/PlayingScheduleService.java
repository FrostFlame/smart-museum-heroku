package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.Exposition;
import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
public interface PlayingScheduleService {

    List<PlayingSchedule> getAllPlayingSchedule();

    PlayingSchedule addPlayingSchedule(PlayingSchedule playingSchedule);

    List<PlayingSchedule> getPlayingScheduleByProjectors(List<Long> projectors);

    List<PlayingSchedule> getPlayingScheduleByProjectorsByWeekDays(List<Long> projectors, List<Long> weekDays);

    void save(PlayingSchedule playingSchedule);

    void deleteAllBetween(PlayingSchedule playingSchedule);


    void delete(PlayingSchedule playingSchedule);

    PlayingSchedule getOneWhereBeginTimeBefore(PlayingSchedule playingSchedule);

    PlayingSchedule getOneWhereBeginTimeAfter(PlayingSchedule playingSchedule);
}
