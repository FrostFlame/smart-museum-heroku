package ru.kpfu.itis.group11501.smartmuseum.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.group11501.smartmuseum.model.User;
import ru.kpfu.itis.group11501.smartmuseum.model.Video;
import ru.kpfu.itis.group11501.smartmuseum.security.UserDetails;

import java.io.*;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Created by volkov on 19.04.2018.
 */

public class Helpers  {

    public static User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!(principal instanceof UserDetails)) {
            return null;
        }
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetails) principal).getUser();
    }



}
