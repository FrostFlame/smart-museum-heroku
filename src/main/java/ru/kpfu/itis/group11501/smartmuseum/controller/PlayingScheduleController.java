package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.group11501.smartmuseum.model.*;
import ru.kpfu.itis.group11501.smartmuseum.service.*;
import ru.kpfu.itis.group11501.smartmuseum.util.*;
import ru.kpfu.itis.group11501.smartmuseum.validator.PlayingScheduleAddValidator;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
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
    private PlayingScheduleAddValidator playingScheduleAddValidator;

    public PlayingScheduleController(PlayingScheduleService playingScheduleService, ExpositionService expositionService, WeekDayService weekDayService, PlayingScheduleAddValidator playingScheduleAddValidator) {
        this.playingScheduleService = playingScheduleService;
        this.expositionService = expositionService;
        this.weekDayService = weekDayService;
        this.playingScheduleAddValidator = playingScheduleAddValidator;
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
    public Exposition exposition(@PathVariable(value = "exposition_id", required = false) Long expositionId) {
        if (expositionId == null) return null;
        return expositionService.getExpositionById(expositionId);
    }

    @ModelAttribute("projectors")
    public List<Projector> projectors(@ModelAttribute("exposition") Exposition exposition) {
        if (exposition != null) return exposition.getProjectors();
        return null;
    }

    @RequestMapping(path = "")
    public String getPlayingSchedule() {
        Exposition exposition = expositionService.getFirstExposition();
        if (exposition == null) {
            return "expositions_not_found";
        } else return "redirect:/playing_schedule/" + exposition.getId();
    }


    @RequestMapping(value = "/{exposition_id}", method = RequestMethod.GET)
    public String getPlayingSchedule(Model model, @ModelAttribute("exposition") Exposition exposition,
                                     @RequestParam(value = "weekDays_id", required = false) List<Long> weekDaysId,
                                     @RequestParam(value = "projectors_id", required = false) List<Long> projectorsId,
                                     @RequestParam(value = "sort", required = false) String sort,
                                     @RequestParam(value = "page", required = false) String page,
                                     HttpSession httpSession) {

        if (exposition == null) {
            return "404_not_found";
        }
        if (exposition.getProjectors().size() > 0) {
            if (projectorsId == null || projectorsId.size() == 0) projectorsId = exposition.getProjectors()
                    .stream()
                    .map(Projector::getId)
                    .collect(Collectors.toList());

            Long currentPage = 0L;
            if (page != null) currentPage = Long.valueOf(page);
            Long lastPage = playingScheduleService.getLastPage(weekDaysId, projectorsId);
            List<Long> pages = Helpers.getListPages(currentPage, lastPage, 4L);
            List<PlayingSchedule> playingSchedule = playingScheduleService.getPlayingScheduleByParameters(projectorsId, weekDaysId, sort, currentPage);

            model.addAttribute("playingSchedule", playingSchedule);
            model.addAttribute("pages", pages);
            model.addAttribute("page", currentPage);
            model.addAttribute("lastPage", lastPage);
            model.addAttribute("sort", sort);
            httpSession.setAttribute("weekDays_id", weekDaysId);
            httpSession.setAttribute("projectors_id", projectorsId);
            httpSession.setAttribute("sort", sort);
        }
        return "playing_schedule";
    }

    @RequestMapping(value = "/{exposition_id}/goToAnotherPage", method = RequestMethod.GET)
    public String getPlayingSchedule(@PathVariable("exposition_id") Long expositionId,
                                     @RequestParam(value = "page", required = false) String page,
                                     RedirectAttributes redirectAttributes,
                                     HttpSession httpSession) {


        redirectAttributes.addAttribute("weekDays_id", httpSession.getAttribute("weekDays_id"));
        redirectAttributes.addAttribute("projectors_id", httpSession.getAttribute("projectors_id"));
        redirectAttributes.addAttribute("sort", httpSession.getAttribute("sort"));
        redirectAttributes.addAttribute("page", page);
        return "redirect:/playing_schedule/" + expositionId;
    }

    @RequestMapping(value = "/{exposition_id}/add", method = RequestMethod.GET)
    public String addPlayingSchedule(Model model,
                                     @ModelAttribute("exposition") Exposition exposition) {
        if (exposition == null) {
            return "404_not_found";
        } else {
            if (!model.containsAttribute("form")) model.addAttribute("form", new PlayingScheduleAddForm());
        }
        return "add_playing_schedule";
    }

    @RequestMapping(value = "/{exposition_id}/add", method = RequestMethod.POST)
    public String addPlayingSchedule(@ModelAttribute("form") @Valid PlayingScheduleAddForm form,
                                     BindingResult bindingResult,
                                     @PathVariable("exposition_id") Long expositionId,
                                     RedirectAttributes redirectAttributes) {

        playingScheduleAddValidator.validate(form, bindingResult);
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.form", bindingResult);
            redirectAttributes.addFlashAttribute("form", form);
            return "redirect:/playing_schedule/" + expositionId + "/add";
        }

        playingScheduleService.addPlayingScheduleByParameters(form.getProjectorsId(), form.getWeekDaysId(), form.getBeginTime(), form.getEndTime());
        return "redirect:/playing_schedule/" + expositionId;
    }


    @RequestMapping(value = "/{exposition_id}/delete", method = RequestMethod.POST)
    public String deletePlayingSchedule(@PathVariable("exposition_id") Long expositionId,
                                        @RequestParam(value = "id", required = true) Long playingScheduleId,
                                        @RequestParam(value = "page", required = false) String page,
                                        RedirectAttributes redirectAttributes,
                                        HttpSession httpSession) {

        playingScheduleService.deleteById(playingScheduleId);
        redirectAttributes.addAttribute("weekDays_id", httpSession.getAttribute("weekDays_id"));
        redirectAttributes.addAttribute("projectors_id", httpSession.getAttribute("projectors_id"));
        redirectAttributes.addAttribute("sort", httpSession.getAttribute("sort"));
        redirectAttributes.addAttribute("page", page);
        return "redirect:/playing_schedule/" + expositionId;
    }


}
