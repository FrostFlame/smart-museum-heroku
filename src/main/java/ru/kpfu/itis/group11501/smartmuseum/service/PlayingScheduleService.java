package ru.kpfu.itis.group11501.smartmuseum.service;

import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
public interface PlayingScheduleService {

    List<PlayingSchedule> getAllPlayingSchedule();

    PlayingSchedule addPlayingSchedule(PlayingSchedule playingSchedule);
}
