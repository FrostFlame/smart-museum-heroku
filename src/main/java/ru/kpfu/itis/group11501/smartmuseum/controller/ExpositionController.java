package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.group11501.smartmuseum.model.Exposition;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.Video;
import ru.kpfu.itis.group11501.smartmuseum.service.ExpositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorService;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorsVideosService;
import ru.kpfu.itis.group11501.smartmuseum.service.VideoService;
import ru.kpfu.itis.group11501.smartmuseum.util.ExpositionForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by volkov on 20.04.2018.
 */
@Controller
@RequestMapping(path = "/expositions")
public class ExpositionController {

    private ExpositionService expositionService;
    private VideoService videoService;
    private ProjectorService projectorService;
    private ProjectorsVideosService projectorsVideosService;

    @Autowired
    public ExpositionController(ExpositionService expositionService, VideoService videoService, ProjectorService projectorService, ProjectorsVideosService projectorsVideosService) {
        this.expositionService = expositionService;
        this.videoService = videoService;
        this.projectorService = projectorService;
        this.projectorsVideosService = projectorsVideosService;
    }

    @ModelAttribute("exposition")
    public Exposition exposition(@PathVariable(value = "id", required = false) Long expositionId) {
        if (expositionId == null) return null;
        return expositionService.getExpositionById(expositionId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String expositionsPage(Model model) {
        model.addAttribute("expositions", expositionService.getAllExposition());
        return "expositions";
    }

    @RequestMapping(value = "/{id}/addVideo", method = RequestMethod.GET)


    public String addVideo(Model model, @ModelAttribute("exposition") Exposition exposition,
                           @ModelAttribute("error") String error) {

        if (exposition == null) {
            return "404_not_found";
        }
        if (error != null && !error.equals("")) {
            model.addAttribute("error", error);
        }


        List<Projector> projectors = exposition.getProjectors();
        List<Video> videos = videoService.getAllVideo();
        model.addAttribute("projectors", projectors);
        model.addAttribute("videos", videos);
        return "add_video";
    }

    @RequestMapping(value = "/{id}/addVideo", method = RequestMethod.POST)
    public String addVideo(@ModelAttribute("exposition") Exposition exposition,
                           @RequestParam(value = "projectors_id", required = false) List<String> projectorsId,
                           @RequestParam(value = "videos_id", required = false) List<String> videosId,
                           RedirectAttributes redirectAttributes) {
        if (projectorsId == null || videosId == null || projectorsId.size() == 0 || videosId.size() == 0) {
            redirectAttributes.addAttribute("error", "Выберите проектор и видео");
            return "redirect:/expositions/" + exposition.getId() + "/addVideo";
        }
        projectorsVideosService.addProjectorsVideosList(projectorsId, videosId);
        return "redirect:/expositions/" + exposition.getId();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddExpositionPage(Model model, @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        model.addAttribute("form", new ExpositionForm());
        model.addAttribute("projectors", projectorService.getFreeProjectors());
        return "add_exposition";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addExposition(@ModelAttribute("form") @Valid ExpositionForm expositionForm,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || expositionService.findOneByName(expositionForm.getName()) != null) {
            redirectAttributes.addAttribute("error", "Неправильное название");
            if (bindingResult.hasFieldErrors("projectorsId")) {
                redirectAttributes.addAttribute("error", "Выберите проектор");
            }
            return "redirect:/expositions/add";
        }
        Exposition exposition = expositionService.save(expositionForm.getName(), expositionForm.getProjectorsId());
        return "redirect:/expositions/" + exposition.getId();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getExpositionPage(Model model, @ModelAttribute("exposition") Exposition exposition) {
        if (exposition == null) {
            return "404_not_found";
        }
        return "exposition";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String getExpositionEditPage(Model model, @ModelAttribute("exposition") Exposition exposition) {
        if (exposition == null) {
            return "404_not_found";
        }
        model.addAttribute("projectors", projectorService.getFreeProjectors());
        return "exposition_edit";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.POST)
    public String expositionEdit(@PathVariable("id") String id,
                                 @RequestParam("name") String name,
                                 @RequestParam(value = "delete_projector", required = false) List<String> deleteProjectors,
                                 @RequestParam(value = "new_projectors", required = false) List<String> newProjectors) {
        expositionService.editExposition(Long.valueOf(id), name, deleteProjectors, newProjectors);
        return "redirect:/expositions/" + id;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deleteExposition(@PathVariable("id") String id) {
        expositionService.deleteExposition(Long.valueOf(id));
        return "redirect:/expositions/";
    }

    @RequestMapping(value = "/{id}/turn_on")
    public String turnOnExposition(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        expositionService.turnOn(id);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/{id}/turn_off")
    public String turnOffExposition(@PathVariable(value = "id") Long id, HttpServletRequest request) {
        expositionService.turnOff(id);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchExpositions(Model model, @RequestParam(name = "searchField") String searchField) {
        model.addAttribute("expositions", expositionService.getSearchExpositions(searchField));
        return "expositions";
    }
}
