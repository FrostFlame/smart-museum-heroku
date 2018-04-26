package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.Action;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.CoherentEntity;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.ActionTypeName;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.EntityName;

import java.util.Date;
import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
@CoherentEntity(name = EntityName.PLAYINGSCHEDULE)
public interface PlayingScheduleService {


    List<PlayingSchedule> getAllPlayingSchedule();

    @Action(name = ActionTypeName.ADD)
    PlayingSchedule addPlayingSchedule(PlayingSchedule playingSchedule);

    @Action(name = ActionTypeName.UPDATE)
    void updatePlayingSchedule(PlayingSchedule playingSchedule);

    void deleteAllBetween(PlayingSchedule playingSchedule);


    PlayingSchedule getOneWhereBeginTimeBefore(PlayingSchedule playingSchedule);

    PlayingSchedule getOneWhereBeginTimeAfter(PlayingSchedule playingSchedule);

    @Action(name = ActionTypeName.DELETE)
    void deleteById(Long playingScheduleId);


    List<PlayingSchedule> getPlayingScheduleByParameters(List<Long> projectorsId, List<Long> weekDaysId, String sort);

    void addPlayingScheduleByParameters(List<String> projectorsId, List<String> weekDaysId, Date beginTime, Date endTime);
}
