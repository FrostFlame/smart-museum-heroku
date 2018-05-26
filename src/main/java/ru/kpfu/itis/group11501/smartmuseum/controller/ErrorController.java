package ru.kpfu.itis.group11501.smartmuseum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Amir Kadyrov
 * Date: 26.05.2018
 */
@Controller
public class ErrorController {

    @RequestMapping(value = "/error")
    public String getErrorPage(){
        return "404_not_found";
    }
}
