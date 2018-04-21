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
    public Exposition exposition(@PathVariable(value = "exposition_id",required = false) Long expositionId) {
        if (expositionId == null) return null;
        return expositionService.getExpositionById(expositionId);
    }


    @RequestMapping(value = "/{exposition_id}/addVideo", method = RequestMethod.GET)
    public String addVideo(Model model, @ModelAttribute("exposition") Exposition exposition,
                                      @ModelAttribute("error") String error) {

        if (error != null && !error.equals("")){
            return "add_video";
        }

        if (exposition == null ) {
            model.addAttribute("error", "Экспозиция не найдена");
            return "add_video";
        }


        List<Projector> projectors = exposition.getProjectors();
        List<Video> videos = videoService.getAllVideo();
        model.addAttribute("projectors",projectors);
        model.addAttribute("videos",videos);
        return "add_video";
    }

    @RequestMapping(value = "/{exposition_id}/addVideo", method = RequestMethod.POST)
    public String addVideo(@ModelAttribute("exposition") Exposition exposition,
                                     @RequestParam(value = "projectors_id", required = false)  List<String> projectorsId,
                                     @RequestParam(value = "videos_id", required = false)  List<String> videosId
                                     ) {

        for(String projectorId: projectorsId){
            for (String videoId: videosId){
                if(projectorsVideosService.getProjectorsVideosByProjectorIdByVideoId(Long.decode(projectorId),Long.decode(videoId)) == null){
                    Projector projector = projectorService.getOneById(Long.decode(projectorId));
                    Video video = videoService.findOneById(Long.decode(videoId));
                    Long lastNum =0L;
                    ProjectorsVideos hasLastNum = projectorsVideosService.getProjectorsVideosByProjectorIdWhereLastNum(projector.getId());
                    if (hasLastNum != null) lastNum = hasLastNum.getNum();
                    ProjectorsVideos newProjectorsVideos = new ProjectorsVideos(video,projector,++lastNum);
                    projectorsVideosService.addProjectorsVideos(newProjectorsVideos);
                }
            }
        }
        return  "redirect:/expositions/"+exposition.getId();
    }

    @RequestMapping(name = "/add", method = RequestMethod.GET)
    public String getAddExpositionPage(Model model) {
        model.addAttribute("form", new ExpositionForm());
        model.addAttribute("projectors", projectorService.getFreeProjectors());
        return "add_exposition";
    }

    @RequestMapping(name = "/add", method = RequestMethod.POST)
    public String addExposition(@ModelAttribute("form") @Valid ExpositionForm expositionForm) {
        Exposition exposition = new Exposition();
        exposition.setId(1L);
        return  "redirect:/expositions/"+ exposition.getId();
    }
}
