package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.group11501.smartmuseum.service.ExpositionService;
import ru.kpfu.itis.group11501.smartmuseum.service.ProjectorService;
import ru.kpfu.itis.group11501.smartmuseum.util.ExpositionForm;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/add_exposition")
public class ExpositionController {
    private ProjectorService projectorService;
    private ExpositionService expositionService;

    @Autowired
    public ExpositionController(ProjectorService projectorService, ExpositionService expositionService) {
        this.projectorService = projectorService;
        this.expositionService = expositionService;

    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAddExpositionPage(Model model) {
        model.addAttribute("form", new ExpositionForm());
        model.addAttribute("projectors", projectorService.getFreeProjectors());
        return "add_exposition";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addExposition(@ModelAttribute("form") @Valid ExpositionForm expositionForm) {
//        model.addAttribute("form", new ExpositionForm());
        return "exposition_page";
    }
}
