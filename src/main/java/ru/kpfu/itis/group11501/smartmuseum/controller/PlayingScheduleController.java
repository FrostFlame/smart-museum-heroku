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

    @Autowired
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
    public String getPlayingSchedule(Model model) {
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

        List<PlayingSchedule> playingSchedule = new ArrayList<>();
        if (projectorsId == null){
            projectorsId = new ArrayList<>();
            for(Projector p: exposition.getProjectors()){
                projectorsId.add(p.getId());
            }
        }

        if (projectorsId.size()!=0) {
            if (weekDaysId == null) {
                if (sort !=null && sort.equals("projectors")) playingSchedule = playingScheduleService.getPlayingScheduleByProjectorsSortByProjector(projectorsId);
                else playingSchedule = playingScheduleService.getPlayingScheduleByProjectors(projectorsId);
            } else {
                if (sort !=null && sort.equals("projectors")) playingSchedule = playingScheduleService.getPlayingScheduleByProjectorsByWeekDaysSortByProjector(projectorsId, weekDaysId);
                else playingSchedule = playingScheduleService.getPlayingScheduleByProjectorsByWeekDays(projectorsId, weekDaysId);
            }
        }
        model.addAttribute("playingSchedule", playingSchedule);
        return "playing_schedule";
    }

    @RequestMapping(value = "/{exposition_id}/add", method = RequestMethod.GET)
    public String addPlayingSchedule(Model model,
                                     @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("error",error );
        model.addAttribute("form", new PlayingScheduleAddForm());
        return "add_playing_schedule";
    }

    @RequestMapping(value = "/{exposition_id}/add", method = RequestMethod.POST)
    public String addPlayingSchedule(@ModelAttribute("form") @Valid PlayingScheduleAddForm form,
                                     BindingResult bindingResult,
                                     @PathVariable("exposition_id") Long expositionId,
                                     RedirectAttributes redirectAttributes) {
        if( bindingResult.hasErrors() || form.getBeginTime().compareTo(form.getEndTime())>=0) {
            redirectAttributes.addAttribute("error",  "Поля заполнены не верно");
            return  "redirect:/playing_schedule/"+expositionId+"/add";
        }

        for(String projectorId: form.getProjectorsId()){
            for (String weekDayId: form.getWeekDaysId()){
                Projector projector = projectorService.getOneById(Long.decode(projectorId));
                WeekDay weekDay = weekDayService.getOneById(Long.decode(weekDayId));
                PlayingSchedule newPlayingSchedule = new PlayingSchedule(form.getBeginTime(),form.getEndTime(),weekDay,projector);

                playingScheduleService.deleteAllBetween(newPlayingSchedule);

                PlayingSchedule playingScheduleBefore = playingScheduleService.getOneWhereBeginTimeBefore(newPlayingSchedule);
                PlayingSchedule playingScheduleAfter = playingScheduleService.getOneWhereBeginTimeAfter(newPlayingSchedule);

                if (playingScheduleBefore == null || playingScheduleBefore.getEndTime().compareTo(newPlayingSchedule.getBeginTime()) < 0) {
                    if (playingScheduleAfter == null || playingScheduleAfter.getBeginTime().compareTo(newPlayingSchedule.getEndTime()) > 0){
                        playingScheduleService.addPlayingSchedule(newPlayingSchedule);
                    }
                    else{
                        playingScheduleAfter.setBeginTime(newPlayingSchedule.getBeginTime());
                        playingScheduleService.save(playingScheduleAfter);

                    }

                }
                else {
                    if (playingScheduleBefore.getEndTime().compareTo(newPlayingSchedule.getEndTime()) < 0) {
                        if (playingScheduleAfter == null || playingScheduleAfter.getBeginTime().compareTo(newPlayingSchedule.getEndTime()) > 0) {
                            playingScheduleBefore.setEndTime(newPlayingSchedule.getEndTime());
                        }
                        else{
                            playingScheduleBefore.setEndTime(playingScheduleAfter.getEndTime());
                        }
                        playingScheduleService.save(playingScheduleBefore);
                    }
                }



            }
        }
        return  "redirect:/playing_schedule/"+expositionId;
    }


    @RequestMapping(value = "/{exposition_id}/delete", method = RequestMethod.POST)
    public String addPlayingSchedule(@PathVariable("exposition_id") Long expositionId,
                                     @RequestParam(value = "id", required = true) Long playingScheduleId) {

        playingScheduleService.deleteById(playingScheduleId);
        return  "redirect:/playing_schedule/"+expositionId;
    }


}
