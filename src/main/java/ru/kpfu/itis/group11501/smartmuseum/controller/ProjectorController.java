package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.group11501.smartmuseum.model.Projector;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorService;
import ru.kpfu.itis.group11501.smartmuseum.util.ProjectorAddForm;

import javax.validation.Valid;

/**
 * Created by Bogdan Popov on 15.04.2018.
 */
@Controller
@RequestMapping("/projector")
public class ProjectorController {

    private ProjectorService projectorService;

    @Autowired
    public ProjectorController(ProjectorService projectorService) {
        this.projectorService = projectorService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String addProjector(Model model) {
        model.addAttribute("projectorForm", new ProjectorAddForm());
        return "create_projector";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String addProjector(@ModelAttribute("projectorForm") @Valid ProjectorAddForm form,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }
        Projector projector = new Projector();
        projector.setName(form.getName());
        projectorService.add(projector);
        return "redirect:/projector/create";
    }
}
