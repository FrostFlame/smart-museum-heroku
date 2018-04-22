package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.model.ProjectorsVideos;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorService;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorsVideosService;
import ru.kpfu.itis.group11501.smartmuseum.util.ProjectorAddForm;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Bogdan Popov on 15.04.2018.
 */
@Controller
@RequestMapping("/projector")
public class ProjectorController {

    private ProjectorService projectorService;
    private ProjectorsVideosService projectorsVideosService;

    @Autowired
    public ProjectorController(ProjectorService projectorService, ProjectorsVideosService projectorsVideosService) {
        this.projectorService = projectorService;
        this.projectorsVideosService = projectorsVideosService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addProjector(@ModelAttribute("projectorForm") @Valid ProjectorAddForm form,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        Projector projector = new Projector();
        projector.setName(form.getName());
        projector.setStatus('D');
        projector.setSumTime(0L);
        projectorService.add(projector);
        return "redirect:/projector/all";
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public String allProjectors(Model model) {
        List<Projector> projectors = projectorService.getAllProjectors();
        model.addAttribute("projectors", projectors);
        model.addAttribute("projectorForm", new ProjectorAddForm());
        return "projectors";
    }

    @RequestMapping(value = "/{id}")
    public String getProjector(Model model, @PathVariable(value = "id") Long id) {
        Projector projector = projectorService.getOneById(id);
        List<ProjectorsVideos> projectorsVideos = projectorsVideosService.getProjectorVideos(projector);
        model.addAttribute("projectorVideos", projectorsVideos);
        model.addAttribute("projector", projector);
        return "projector";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String deleteProjector(@PathVariable(value = "id") Long id) {
        projectorService.deleteProjector(id);
        return "redirect:/projector/all";
    }
}
