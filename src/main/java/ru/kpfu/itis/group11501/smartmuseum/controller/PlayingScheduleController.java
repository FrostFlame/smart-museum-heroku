package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;
import ru.kpfu.itis.group11501.smartmuseum.service.PlayingScheduleService;

import java.util.List;

/**
 * Created by volkov on 12.04.2018.
 */
@Controller
public class PlayingScheduleController {

    private PlayingScheduleService playingScheduleService;

    @Autowired
    public PlayingScheduleController(PlayingScheduleService playingScheduleService) {
        this.playingScheduleService = playingScheduleService;
    }

    @RequestMapping(value = "/playing_schedule", method = RequestMethod.GET)
    public String getPlayingSchedule(Model model) {
        List<PlayingSchedule> playingSchedule = playingScheduleService.getAllPlayingSchedule();
        model.addAttribute("playingSchedule", playingSchedule);
        return "playing_schedule";
    }
}
