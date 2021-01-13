package net.plethora.folvark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchportalController {

    @GetMapping("/archportal")
    public String pageArchportal(){

        return "archportal-page";
    }

}
