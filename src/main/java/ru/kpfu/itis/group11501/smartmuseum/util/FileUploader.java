package ru.kpfu.itis.group11501.smartmuseum.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.group11501.smartmuseum.model.Video;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by volkov on 20.04.2018.
 */
@Component
public class FileUploader {

    private String videoPath = "";

    private String imagePath = "";

    public String getVideoPath() {
        return videoPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setVideoPath(String videoPath) {
        if (!new File(videoPath).exists()) new File(videoPath).mkdirs();
        this.videoPath = videoPath;
    }

    public void setImagePath(String imagePath) {
        if (!new File(imagePath).exists()) new File(imagePath).mkdirs();
        this.imagePath = imagePath;
    }

    public String uploadVideo(MultipartFile video, String name) {
        if (video == null || video.getSize() <= 0) {
            return null;
        }
        String extension = FilenameUtils.getExtension(video.getOriginalFilename()).toLowerCase();
        if (!extension.equals("mp4") && !extension.equals("ogv") && !extension.equals("webm") && !extension.equals("avi"))
            return null;
        name = name + "." + extension;
        return uploadFile(video, name, videoPath);
    }

    public String deleteVideo(Video video) {
        return deleteFile(video.getName(), videoPath);
    }

    public String uploadImage(MultipartFile image) {
        if (image == null || image.getSize() <= 0) {
            return null;
        }
        String extension = FilenameUtils.getExtension(image.getOriginalFilename()).toLowerCase();
        if (!extension.equals("jpeg") && !extension.equals("jpg") && !extension.equals("png") && !extension.equals("webp"))
            return null;
        String name = RandomStringUtils.randomAlphanumeric(8) + "." + FilenameUtils.getExtension(image.getOriginalFilename());


        name = uploadFile(image, name, imagePath);
        if (name != null) {
            BufferedImage scaled = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = scaled.createGraphics();
            File savedImg = new File(imagePath + name);
            try {
                g.drawImage(ImageIO.read(savedImg), 0, 0, 500, 500, null);
                g.dispose();
                ImageIO.write(scaled, "JPG", savedImg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return name;
    }

    public String deleteImage(String name) {
        return deleteFile(name, imagePath);
    }


    private String uploadFile(MultipartFile file, String name, String path) {

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                File uploadedFile = new File(path + name);
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

    private String deleteFile(String name, String path) {
        try {
            FileUtils.forceDelete(FileUtils.getFile(path, name));
        } catch (Exception e) {
            return null;
        }
        return "";
    }
}
