package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.WeekDay;
import ru.kpfu.itis.group11501.smartmuseum.model.annotation.Action;
import ru.kpfu.itis.group11501.smartmuseum.model.enums.ActionTypeName;
import ru.kpfu.itis.group11501.smartmuseum.repository.PlayingScheduleRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.PlayingScheduleService;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorService;
import ru.kpfu.itis.group11501.smartmuseum.service.WeekDayService;

import java.util.Date;
import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
@Service
public class PlayingScheduleServiceImpl implements PlayingScheduleService {

    private PlayingScheduleRepository playingScheduleRepository;
    private ProjectorService projectorService;
    private WeekDayService weekDayService;
    private static int size = 5;

    @Autowired
    private PlayingScheduleService playingScheduleService;

    public PlayingScheduleServiceImpl(PlayingScheduleRepository playingScheduleRepository, ProjectorService projectorService, WeekDayService weekDayService) {
        this.playingScheduleRepository = playingScheduleRepository;
        this.projectorService = projectorService;
        this.weekDayService = weekDayService;
    }

    @Override
    public List<PlayingSchedule> getAllPlayingSchedule() {
        return playingScheduleRepository.findAll();
    }

    @Override
    @Action(name = ActionTypeName.ADD)
    public PlayingSchedule addPlayingSchedule(PlayingSchedule playingSchedule) {
        return playingScheduleRepository.save(playingSchedule);
    }

    @Override
    @Action(name = ActionTypeName.UPDATE)
    public void updatePlayingSchedule(PlayingSchedule playingSchedule) {
        playingScheduleRepository.save(playingSchedule);
    }

    @Override
    public void deleteAllBetween(PlayingSchedule playingSchedule) {
        playingScheduleRepository.deleteAllBetween(
                playingSchedule.getProjector().getId(),
                playingSchedule.getWeekDay().getId(),
                playingSchedule.getBeginTime(),
                playingSchedule.getEndTime());
    }

    @Override
    public PlayingSchedule getOneWhereBeginTimeBefore(PlayingSchedule playingSchedule) {
        return playingScheduleRepository.getOneWhereBeginTimeBefore(playingSchedule.getProjector().getId(),
                playingSchedule.getWeekDay().getId(),
                playingSchedule.getBeginTime());
    }

    @Override
    public PlayingSchedule getOneWhereBeginTimeAfter(PlayingSchedule playingSchedule) {
        return playingScheduleRepository.getOneWhereBeginTimeAfter(playingSchedule.getProjector().getId(),
                playingSchedule.getWeekDay().getId(),
                playingSchedule.getBeginTime());
    }

    @Override
    @Action(name = ActionTypeName.DELETE)
    public void deleteById(Long playingScheduleId) {
        playingScheduleRepository.deleteById(playingScheduleId);
    }

    @Override
    public List<PlayingSchedule> getPlayingScheduleByParameters(List<Long> projectorsId, List<Long> weekDaysId, String sort, Long page) {

        Pageable pageRequest;
        if (sort != null && sort.equals("projectors")) {
            pageRequest = new PageRequest(page.intValue(), size, Sort.Direction.ASC, "projector.name", "weekDay", "beginTime");
        } else {
            pageRequest = new PageRequest(page.intValue(), size, Sort.Direction.ASC, "weekDay", "beginTime", "projector.name");
        }

        if (weekDaysId == null || weekDaysId.size() == 0) {
            return playingScheduleRepository.getPlayingScheduleByProjectors(projectorsId, pageRequest);
        } else {
            return playingScheduleRepository.getPlayingScheduleByProjectorsByWeekDay(projectorsId, weekDaysId, pageRequest);
        }
    }

    @Override
    public void addPlayingScheduleByParameters(List<String> projectorsId, List<String> weekDaysId, Date beginTime, Date endTime) {
        for (String projectorId : projectorsId) {
            for (String weekDayId : weekDaysId) {
                Projector projector = projectorService.getOneById(Long.decode(projectorId));
                WeekDay weekDay = weekDayService.getOneById(Long.decode(weekDayId));
                PlayingSchedule newPlayingSchedule = new PlayingSchedule(beginTime, endTime, weekDay, projector);

                playingScheduleService.deleteAllBetween(newPlayingSchedule);
                PlayingSchedule playingScheduleBefore = getOneWhereBeginTimeBefore(newPlayingSchedule);
                PlayingSchedule playingScheduleAfter = getOneWhereBeginTimeAfter(newPlayingSchedule);

                if (playingScheduleBefore == null || playingScheduleBefore.getEndTime().compareTo(newPlayingSchedule.getBeginTime()) < 0) {
                    if (playingScheduleAfter == null || playingScheduleAfter.getBeginTime().compareTo(newPlayingSchedule.getEndTime()) > 0) {
                        playingScheduleService.addPlayingSchedule(newPlayingSchedule);
                    } else {
                        playingScheduleAfter.setBeginTime(newPlayingSchedule.getBeginTime());
                        playingScheduleService.updatePlayingSchedule(playingScheduleAfter);

                    }

                } else {
                    if (playingScheduleBefore.getEndTime().compareTo(newPlayingSchedule.getEndTime()) < 0) {
                        if (playingScheduleAfter == null || playingScheduleAfter.getBeginTime().compareTo(newPlayingSchedule.getEndTime()) > 0) {
                            playingScheduleBefore.setEndTime(newPlayingSchedule.getEndTime());
                        } else {
                            playingScheduleBefore.setEndTime(playingScheduleAfter.getEndTime());
                            playingScheduleService.deleteById(playingScheduleAfter.getId());
                        }
                        playingScheduleService.updatePlayingSchedule(playingScheduleBefore);
                    }
                }


            }
        }
    }

    @Override
    public Long getLastPage(List<Long> weekDaysId, List<Long> projectorsId) {
        Long rows;
        if (weekDaysId == null || weekDaysId.size() == 0) {
            rows = playingScheduleRepository.getCountRow(projectorsId);
        } else {
            rows = playingScheduleRepository.getCountRowByWeekDay(projectorsId, weekDaysId);
        }
        if (rows % size == 0) return rows / size - 1;
        else return rows / size;
    }


}
