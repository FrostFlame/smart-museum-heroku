package ru.kpfu.itis.group11501.smartmuseum.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
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

    public String getVideoPath() {
        return videoPath;
    }

    public String getImagePath() {
        return imagePath;
    }

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
        return uploadFile(video,name,videoPath);
    }

    public  String deleteVideo(Video video) {
        return deleteFile(video.getName(),videoPath);
    }

    public String uploadImage(MultipartFile image) {
        if(image == null || image.getSize() <= 0) {
            return null;
        }

        String name = RandomStringUtils.randomAlphanumeric(8) + "." + FilenameUtils.getExtension(image.getOriginalFilename());
        return uploadFile(image,name,imagePath);
    }

    public  String deleteImage(String name) {
        return deleteFile(name,imagePath);
    }


    private String uploadFile(MultipartFile file, String name, String path) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File uploadedFile = new File(path + name );
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

    private  String deleteFile(String name,String path) {
        try {
            FileUtils.forceDelete(FileUtils.getFile(path,name));
        } catch (Exception e) {
            return null;
        }
        return "";
    }
}
