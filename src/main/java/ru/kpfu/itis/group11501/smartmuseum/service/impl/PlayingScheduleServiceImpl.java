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
    public List<PlayingSchedule> getPlayingScheduleByProjectors(List<Long> projectorsId) {
        return playingScheduleRepository.getPlayingScheduleByProjectorsId(projectorsId);
    }

    @Override
    public List<PlayingSchedule> getPlayingScheduleByProjectorsByWeekDays(List<Long> projectorsId, List<Long> weekDaysId) {
        return playingScheduleRepository.getPlayingScheduleByProjectorsByWeekDay(projectorsId,weekDaysId);
    }

    @Override
    public List<PlayingSchedule> getPlayingScheduleByProjectorsSortByProjector(List<Long> projectorsId) {
        return playingScheduleRepository.getPlayingScheduleByProjectorsIdSortByProjector(projectorsId);
    }

    @Override
    public List<PlayingSchedule> getPlayingScheduleByProjectorsByWeekDaysSortByProjector(List<Long> projectorsId, List<Long> weekDaysId) {
        return playingScheduleRepository.getPlayingScheduleByProjectorsByWeekDaySortByProjector(projectorsId,weekDaysId);
    }

    @Override
    public void save(PlayingSchedule playingSchedule){
        playingScheduleRepository.save(playingSchedule);
    }

    @Override
    public void deleteAllBetween(PlayingSchedule playingSchedule){
        playingScheduleRepository.deleteAllBetween(
                playingSchedule.getProjector().getId(),
                playingSchedule.getWeekDay().getId(),
                playingSchedule.getBeginTime(),
                playingSchedule.getEndTime());
    }

    @Override
    public void delete(PlayingSchedule playingSchedule){
        playingScheduleRepository.delete(playingSchedule);
    }

    @Override
    public PlayingSchedule getOneWhereBeginTimeBefore(PlayingSchedule playingSchedule){
        return playingScheduleRepository.getOneWhereBeginTimeBefore(playingSchedule.getProjector().getId(),
                playingSchedule.getWeekDay().getId(),
                playingSchedule.getBeginTime());
    }

    @Override
    public PlayingSchedule getOneWhereBeginTimeAfter(PlayingSchedule playingSchedule){
        return playingScheduleRepository.getOneWhereBeginTimeAfter(playingSchedule.getProjector().getId(),
                playingSchedule.getWeekDay().getId(),
                playingSchedule.getBeginTime());
    }

    @Override
    public void deleteById(Long playingScheduleId) {
        playingScheduleRepository.deleteById(playingScheduleId);
    }


}
