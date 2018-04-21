package ru.kpfu.itis.group11501.smartmuseum.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.group11501.smartmuseum.model.Video;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by volkov on 20.04.2018.
 */
@Component
public class FileUploader {

    private String videoPath ="";

    private String imagePath="";

    public void setVideoPath(String videoPath) {
        if(! new File(videoPath).exists()) new File(videoPath).mkdirs();
        this.videoPath = videoPath;
    }

    public void setImagePath(String imagePath) {
        if(! new File(imagePath).exists()) new File(imagePath).mkdirs();
        this.imagePath = imagePath;
    }

    public String uploadVideo(MultipartFile video, String name) {
        if(video == null || video.getSize() <= 0) {
            return null;
        }

        name = name + "." + FilenameUtils.getExtension(video.getOriginalFilename());
        if (!video.isEmpty()) {
            try {
                byte[] bytes = video.getBytes();
                File uploadedFile = new File(videoPath + name );
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                return name;

            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public  String deleteVideo(Video video) {
        try {
            FileUtils.forceDelete(FileUtils.getFile(videoPath,video.getName()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return "";
    }
}
