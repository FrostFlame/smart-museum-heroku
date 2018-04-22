package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.group11501.smartmuseum.model.Exposition;
import ru.kpfu.itis.group11501.smartmuseum.model.PlayingSchedule;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.WeekDay;
import ru.kpfu.itis.group11501.smartmuseum.service.ExpositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.PlayingScheduleService;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorService;
import ru.kpfu.itis.group11501.smartmuseum.service.WeekDayService;
import ru.kpfu.itis.group11501.smartmuseum.util.PlayingScheduleAddForm;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by volkov on 12.04.2018.
 */
@Controller
@RequestMapping(path = "/playing_schedule")
public class PlayingScheduleController {

    private PlayingScheduleService playingScheduleService;
    private ExpositionService expositionService;
    private WeekDayService weekDayService;
    private ProjectorService projectorService;


    public PlayingScheduleController(PlayingScheduleService playingScheduleService,
                                     ExpositionService expositionService,
                                     WeekDayService weekDayService,
                                     ProjectorService projectorService) {
        this.playingScheduleService = playingScheduleService;
        this.expositionService = expositionService;
        this.weekDayService = weekDayService;
        this.projectorService = projectorService;
    }



    @ModelAttribute("expositions")
    public List<Exposition> expositions() {
        return expositionService.getAllExposition();
    }

    @ModelAttribute("weekDays")
    public List<WeekDay> weekDays() {
        return weekDayService.getAllWeekDay();
    }

    @ModelAttribute("exposition")
    public Exposition exposition(@PathVariable(value = "exposition_id",required = false) Long expositionId) {
        if (expositionId == null) return null;
        return expositionService.getExpositionById(expositionId);
    }

    @ModelAttribute("projectors")
    public List<Projector> projectors(@ModelAttribute("exposition") Exposition exposition) {
        if (exposition!= null )return exposition.getProjectors();
        return null;
    }

    @RequestMapping(path = "")
    public String getPlayingSchedule() {
        Exposition exposition = expositionService.getFirstExposition();
        if (exposition == null) {
            return  "expositions_not_found";
        }
        else return  "redirect:/playing_schedule/"+exposition.getId();
    }


    @RequestMapping(value = "/{exposition_id}", method = RequestMethod.GET)
    public String getPlayingSchedule(Model model,@ModelAttribute("exposition") Exposition exposition,
                                     @ModelAttribute("error") String error,
                                     @RequestParam(value = "weekDays_id", required = false)  List<Long> weekDaysId,
                                     @RequestParam(value = "projectors_id", required = false)  List<Long> projectorsId,
                                     @RequestParam(value = "sort", required = false)  String sort) {


        if (error != null && !error.equals("")){
            return "playing_schedule";
        }

        if (exposition == null ) {
            model.addAttribute("error", "Экспозиция не найдена");
            return "playing_schedule";
        }
        if (exposition.getProjectors().size()>0) {
            if (projectorsId == null) projectorsId = exposition.getProjectors()
                    .stream()
                    .map(Projector::getId)
                    .collect(Collectors.toList());
            List<PlayingSchedule> playingSchedule = playingScheduleService.getPlayingScheduleByParameters(projectorsId,weekDaysId,sort);
            model.addAttribute("playingSchedule", playingSchedule);
        }
        return "playing_schedule";
    }

    @RequestMapping(value = "/{exposition_id}/add", method = RequestMethod.GET)
    public String addPlayingSchedule(Model model,
                                     @RequestParam(value = "error", required = false) String error,
                                     @ModelAttribute("exposition") Exposition exposition) {
        if( exposition == null) {
            model.addAttribute("error", "Экспозиция не найдена");
        }
        else{
            model.addAttribute("error",error );
            model.addAttribute("form", new PlayingScheduleAddForm());
        }
        return "add_playing_schedule";
    }

    @RequestMapping(value = "/{exposition_id}/add", method = RequestMethod.POST)
    public String addPlayingSchedule(@ModelAttribute("form") @Valid PlayingScheduleAddForm form,
                                     BindingResult bindingResult,
                                     @PathVariable("exposition_id") Long expositionId,
                                     RedirectAttributes redirectAttributes) {
        if( bindingResult.hasErrors() || form.getBeginTime()==null || form.getEndTime()==null || form.getBeginTime().compareTo(form.getEndTime())>=0) {
            redirectAttributes.addAttribute("error",  "Поля заполнены не верно");
            return  "redirect:/playing_schedule/"+expositionId+"/add";
        }

        playingScheduleService.addPlayingScheduleByParameters(form.getProjectorsId(),form.getWeekDaysId(),form.getBeginTime(),form.getEndTime());
        return  "redirect:/playing_schedule/"+expositionId;
    }


    @RequestMapping(value = "/{exposition_id}/delete", method = RequestMethod.POST)
    public String deletePlayingSchedule(@PathVariable("exposition_id") Long expositionId,
                                     @RequestParam(value = "id", required = true) Long playingScheduleId) {

        playingScheduleService.deleteById(playingScheduleId);
        return  "redirect:/playing_schedule/"+expositionId;
    }


}
