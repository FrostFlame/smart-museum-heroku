package ru.kpfu.itis.group11501.smartmuseum.service.impl;

import ru.kpfu.itis.group11501.smartmuseum.model.WeekDay;
import ru.kpfu.itis.group11501.smartmuseum.repository.WeekDayRepository;
import ru.kpfu.itis.group11501.smartmuseum.service.WeekDayService;

import java.util.List;

/**
 * Created by volkov on 13.04.2018.
 */
public class WeekDayServiceImpl implements WeekDayService {

    private WeekDayRepository weekDayRepository;

    public WeekDayServiceImpl(WeekDayRepository weekDayRepository) {
        this.weekDayRepository = weekDayRepository;
    }

    @Override
    public List<WeekDay> getAllWeekDay(){
        return weekDayRepository.findAll();
    }

    @Override
    public WeekDay getOneById(Long projectorId){
        return weekDayRepository.getOne(projectorId);
    }
}
