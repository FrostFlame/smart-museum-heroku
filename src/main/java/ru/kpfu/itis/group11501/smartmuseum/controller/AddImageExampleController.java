package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.group11501.smartmuseum.util.FileUploader;

/**
 * Created by volkov on 07.05.2018.
 */
@Controller
@RequestMapping(path = "/image")
public class AddImageExampleController {
    @Autowired
    private FileUploader fileUploader;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getImage(Model model) {
        return "uploadImage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addImage(@RequestParam(value = "file") MultipartFile file) {


        String name = fileUploader.uploadImage(file);
        if (name == null ) {
            System.out.println("Не удалось добавить фото");
        }
        else{
            System.out.println(name);
        }

        return  "redirect:/image";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String addImage(@RequestParam(value = "name") String name) {



        if (fileUploader.deleteImage(name)==null){
            System.out.println("Не удалось удалить фото");
            return  "redirect:/image";
        }
        System.out.println("Фото удалено");
        return  "redirect:/image";
    }
}
