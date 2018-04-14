package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.group11501.smartmuseum.model.Exposition;
import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.service.ExpositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.PlayingScheduleService;
import ru.kpfu.itis.group11501.smartmuseum.service.WeekDayService;
import ru.kpfu.itis.group11501.smartmuseum.util.PlayingScheduleCreateForm;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
@Controller
@RequestMapping(path = "/playing_schedule")
public class PlayingScheduleController {

    private PlayingScheduleService playingScheduleService;
    private ExpositionService expositionService;
    private WeekDayService weekDayService;

    @Autowired
    public PlayingScheduleController(PlayingScheduleService playingScheduleService,
                                     ExpositionService expositionService,
                                     WeekDayService weekDayService) {
        this.playingScheduleService = playingScheduleService;
        this.expositionService = expositionService;
        this.weekDayService = weekDayService;
    }


    @RequestMapping(path = "")
    public String getPlayingSchedule() {
        Exposition exposition = expositionService.getFirstExposition();
        return  "redirect:/playing_schedule/"+exposition.getId();
    }


    @RequestMapping(value = "/{exposition_id}", method = RequestMethod.GET)
    public String getPlayingSchedule(Model model,@PathVariable("exposition_id") Long exposition_id,
                                     @RequestParam(value = "weekDays_id", required = false)  List<Long> weekDays_id,
                                     @RequestParam(value = "projectors_id", required = false)  List<Long> projectors_id) {
        List<PlayingSchedule> playingSchedule;
        Exposition exposition = expositionService.getExpositionById(exposition_id);

        if (projectors_id == null){
            projectors_id = new ArrayList<>();
            for(Projector p: exposition.getProjectors()){
                projectors_id.add(p.getId());
            }
        }

        if (weekDays_id == null ){
            playingSchedule = playingScheduleService.getPlayingScheduleByProjectors(projectors_id);
        }
        else {
            playingSchedule = playingScheduleService.getPlayingScheduleByProjectorsByWeekDays(projectors_id,weekDays_id);
        }
        List<Exposition> expositions = expositionService.getAllExposition();

        model.addAttribute("expositions", expositions);
        model.addAttribute("playingSchedule", playingSchedule);
        model.addAttribute("projectors", exposition.getProjectors());
        model.addAttribute("weekDay", weekDayService.getAllWeekDay());
        model.addAttribute("form", new PlayingScheduleCreateForm());
        return "playing_schedule";
    }


}
