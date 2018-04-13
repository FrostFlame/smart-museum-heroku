package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.itis.group11501.smartmuseum.model.Exposition;
import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;
import ru.kpfu.itis.group11501.smartmuseum.service.ExpositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.PlayingScheduleService;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
@Controller
public class PlayingScheduleController {

    private PlayingScheduleService playingScheduleService;
    private ExpositionService expositionService;

    @Autowired
    public PlayingScheduleController(PlayingScheduleService playingScheduleService,ExpositionService expositionService) {
        this.playingScheduleService = playingScheduleService;
        this.expositionService = expositionService;
    }

    @RequestMapping(value = "/playing_schedule", method = RequestMethod.GET)
    public String getPlayingSchedule(Model model,@RequestParam(name = "exposition_id", required = true, defaultValue = "1") Long exposition_id) {
        Exposition exposition = expositionService.getExpositionById(exposition_id);
        List<PlayingSchedule> playingSchedule = playingScheduleService.getPlayingScheduleByExposition(exposition);
        model.addAttribute("playingSchedule", playingSchedule);
        return "playing_schedule";
    }
}
