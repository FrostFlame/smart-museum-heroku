package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.group11501.smartmuseum.model.Exposition;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.ProjectorsVideos;
import ru.kpfu.itis.group11501.smartmuseum.model.Video;
import ru.kpfu.itis.group11501.smartmuseum.service.ExpositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorService;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorsVideosService;
import ru.kpfu.itis.group11501.smartmuseum.service.VideoService;
import ru.kpfu.itis.group11501.smartmuseum.util.ExpositionForm;

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
    public Exposition exposition(@PathVariable(value = "exposition_id", required = false) Long expositionId) {
        if (expositionId == null) return null;
        return expositionService.getExpositionById(expositionId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String expositionsPage(Model model) {
        model.addAttribute("expositions", expositionService.getAllExposition());
        return "expositions";
    }

    @RequestMapping(value = "/{exposition_id}/addVideo", method = RequestMethod.GET)


    public String addVideo(Model model,
                           @ModelAttribute("exposition") Exposition exposition,
                           @ModelAttribute("error") String error) {

        if (error != null && !error.equals("")) {
            return "add_video";
        }

        if (exposition == null) {
            model.addAttribute("error", "Экспозиция не найдена");
            return "add_video";
        }


        List<Projector> projectors = exposition.getProjectors();
        List<Video> videos = videoService.getAllVideo();
        model.addAttribute("projectors", projectors);
        model.addAttribute("videos", videos);
        return "add_video";
    }

    @RequestMapping(value = "/{exposition_id}/addVideo", method = RequestMethod.POST)
    public String addVideo(@ModelAttribute("exposition") Exposition exposition,
                           @RequestParam(value = "projectors_id", required = false)  List<String> projectorsId,
                           @RequestParam(value = "videos_id", required = false)  List<String> videosId) {
        projectorsVideosService.addProjectorsVideosList(projectorsId,videosId);
        return  "redirect:/expositions/"+exposition.getId();
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAddExpositionPage(Model model) {
        model.addAttribute("form", new ExpositionForm());
        model.addAttribute("projectors", projectorService.getFreeProjectors());
        return "add_exposition";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addExposition(@ModelAttribute("form") @Valid ExpositionForm expositionForm) {
        Exposition exposition = expositionService.save(expositionForm.getName(), expositionForm.getProjectorsId());
        return "redirect:/expositions/" + exposition.getId();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getExpositionPage(Model model, @PathVariable("id") String id) {
        Exposition exposition = expositionService.getExpositionById(Long.valueOf(id));
        if (exposition == null){
            return "404_not_found";
        }
        model.addAttribute("exposition", exposition);

        return "exposition";
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String getExpositionEditPage(Model model, @PathVariable("id") String id) {
        Exposition exposition = expositionService.getExpositionById(Long.valueOf(id));
        if (exposition == null){
            return "404_not_found";
        }
        model.addAttribute("exposition", exposition);
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

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.POST)
    public String deleteExposition(@PathVariable("id") String id){
        expositionService.deleteExposition(Long.valueOf(id));
        return "redirect:/expositions/";
    }
}
