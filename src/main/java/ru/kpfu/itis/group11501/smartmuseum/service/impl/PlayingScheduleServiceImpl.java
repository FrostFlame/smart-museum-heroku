package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.Exposition;
import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;
import ru.kpfu.itis.group11501.smartmuseum.repository.PlayingScheduleRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.PlayingScheduleService;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
@Service
public class PlayingScheduleServiceImpl implements PlayingScheduleService {

    private PlayingScheduleRepository playingScheduleRepository;

    public PlayingScheduleServiceImpl(PlayingScheduleRepository playingScheduleRepository) {
        this.playingScheduleRepository = playingScheduleRepository;
    }

    @Override
    public List<PlayingSchedule> getAllPlayingSchedule() {
        return playingScheduleRepository.findAll();
    }

    @Override
    public PlayingSchedule addPlayingSchedule(PlayingSchedule playingSchedule) {
        return playingScheduleRepository.save(playingSchedule);
    }

    @Override
    public List<PlayingSchedule> getPlayingScheduleByProjectors(List<Long> projectors_id) {
        return playingScheduleRepository.getPlayingScheduleByExposition(projectors_id);
    }

    @Override
    public List<PlayingSchedule> getPlayingScheduleByProjectorsByWeekDays(List<Long> projectors_id, List<Long> weekDays_id) {
        return playingScheduleRepository.getPlayingScheduleByProjectorsByWeekDay(projectors_id,weekDays_id);
    }
}
