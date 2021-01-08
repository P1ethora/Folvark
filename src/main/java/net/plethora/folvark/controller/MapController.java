package net.plethora.folvark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {

    @GetMapping("/maps")
    public String pageMap(){
        return "map-page";
    }


}
