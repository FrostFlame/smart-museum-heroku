package ru.kpfu.itis.group11501.smartmuseum.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.model.Video;
import ru.kpfu.itis.group11501.smartmuseum.security.UserDetails;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;

/**
 * Created by volkov on 19.04.2018.
 */
public class Helpers {

    //to do right path
    public static String videoPath = "C:\\Users\\volkov\\Desktop\\repozitories\\Smart-museum\\src\\main\\webapp\\resources\\video\\";

    public static User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof UserDetails)) {
            return null;
        }
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails) principal).getUser();
    }

    public static String uploadVideo(MultipartFile video,String name) {
        if(video == null || video.getSize() <= 0) {System.out.println("1");
            return null;
        }

        name = name + "." + FilenameUtils.getExtension(video.getOriginalFilename());
        if (!video.isEmpty()) {
            try {
                byte[] bytes = video.getBytes();
                File uploadedFile = new File(videoPath + name );
                System.out.println(uploadedFile.getAbsolutePath());
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

    public static String deleteVideo(Video video) {
        try {FileUtils.forceDelete(FileUtils.getFile(videoPath,video.getName()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return "";
    }
}
