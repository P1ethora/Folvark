package net.plethora.folvark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/main")
    public String getMain() {

        return "main-page";
    }

    @GetMapping("/")
    public String getMain2() {

        return "main-page";
    }

}
