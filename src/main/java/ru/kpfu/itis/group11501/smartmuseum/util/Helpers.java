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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Created by volkov on 19.04.2018.
 */

public class Helpers  {

    public static User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (User)principal;
    }

    public static List<Long> getListPages(Long currentPage,Long lastPage,Long amountOfVisible){

        Long n = amountOfVisible +1;
        List<Long> result = new ArrayList<>();
        Long amount = 1L;
        boolean flag = false;

        while (!flag && amount<n){
            if (currentPage-amount>=0) result.add(currentPage-amount);
            else flag = true;
            amount++;
        }

        if (flag) amount--;
        n = n * 2 - amount;
        amount = 1L;
        boolean flag2 = false;
        while (!flag2 && amount<n){
            if (currentPage+amount<=lastPage) result.add(currentPage+amount);
            else flag2 = true;
            amount++;
        }


        if (flag2 && !flag) {
            amount--;
            while (!flag  && amount<n){
                if (currentPage-n-(n-amount)+1>=0) result.add(currentPage-n-(n-amount)+1);
                else flag = true;
                amount++;
            }
        }
        result.add(currentPage);
        Collections.sort(result);
        return result;
    }



}
