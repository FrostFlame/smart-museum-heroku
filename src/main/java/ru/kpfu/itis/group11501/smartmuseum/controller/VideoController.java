package ru.kpfu.itis.group11501.smartmuseum.controller;

import com.sun.net.httpserver.HttpServer;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kpfu.itis.group11501.smartmuseum.model.Video;
import ru.kpfu.itis.group11501.smartmuseum.service.VideoService;
import ru.kpfu.itis.group11501.smartmuseum.util.FileUploader;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by volkov on 19.04.2018.
 */
@Controller
@RequestMapping(path = "/videos")
public class VideoController {


    private VideoService videoService;

    private FileUploader fileUploader;

    @Autowired
    public VideoController(VideoService videoService, FileUploader fileUploader) {
        this.videoService = videoService;
        this.fileUploader = fileUploader;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getVideos(Model model,
                            @RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "success", required = false) String success,
                            @RequestParam(value = "searchField", required = false) String searchField) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        if (success != null) {
            model.addAttribute("success", success);
        }
        model.addAttribute("videos", videoService.getAllVideo(searchField));
        return "videos";
    }

    @RequestMapping(value = "/new_video", method = RequestMethod.GET)
    public String addVideo(Model model,
                           @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        return "new_video";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addVideo(@RequestParam(value = "name") String name,
                           @RequestParam(value = "file") MultipartFile file,
                           RedirectAttributes redirectAttributes) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();
        if (videoService.findOneByName(name + "." + extension) != null) {
            redirectAttributes.addAttribute("error", "Видео с таким названием уже существует");
            return "redirect:/videos/new_video";
        }
        name = fileUploader.uploadVideo(file, name);
        if (name == null) {
            redirectAttributes.addAttribute("error", "Не удалось добавить видео");
            return "redirect:/videos/new_video";
        } else {
            Video video = new Video(name);
            videoService.addVideo(video);
        }
        redirectAttributes.addAttribute("success", "Видео успешно добавлено");
        return "redirect:/videos";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteVideo(@RequestParam(value = "id") Long videoId,
                              RedirectAttributes redirectAttributes) {

        if (fileUploader.deleteVideo(videoService.findOneById(videoId)) == null) {
            redirectAttributes.addAttribute("error", "Не удалось удалить файл");
            return "redirect:/videos";
        }
        videoService.deleteById(videoId);
        redirectAttributes.addAttribute("success", "Видео удалено");
        return "redirect:/videos";
    }


}
