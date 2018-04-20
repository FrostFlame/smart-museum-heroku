package ru.kpfu.itis.group11501.smartmuseum.controller;

import com.sun.net.httpserver.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.group11501.smartmuseum.model.Video;
import ru.kpfu.itis.group11501.smartmuseum.service.VideoService;
import ru.kpfu.itis.group11501.smartmuseum.util.Helpers;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by volkov on 19.04.2018.
 */
@Controller
@RequestMapping(path = "/videos")
public class VideoController {


    private VideoService videoService;

    @Autowired
    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getVideos(Model model,
                                     @RequestParam(value = "error", required = false) String error) {
        if (error != null){
            model.addAttribute("error", error);
        }
        model.addAttribute("videos",videoService.getAllVideo());
        return "videos";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addVideos(@RequestParam(value = "name")  String name,
                                     @RequestParam(value = "file") MultipartFile file,
                                     RedirectAttributes redirectAttributes) {

        if (videoService.findOneByName(name) != null){
            redirectAttributes.addAttribute("error",  "Видео с таким названием уже существует");
            return  "redirect:/videos";
        }
        name = Helpers.uploadVideo(file,name);
        if (name == null ) {
            redirectAttributes.addAttribute("error",  "Не удалось добавить видео");
        }
        else{
            Video video = new Video(name);
            videoService.addVideo(video);
        }

        return  "redirect:/videos";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteVideo(@RequestParam(value = "id") Long videoId,
                              RedirectAttributes redirectAttributes) {

        if (Helpers.deleteVideo(videoService.findOneById(videoId)) == null){
            redirectAttributes.addAttribute("error",  "Не удалось удалить файл");
            return  "redirect:/videos";
        }
        videoService.deleteById(videoId);
        return  "redirect:/videos";
    }


}
