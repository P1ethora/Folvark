package net.plethora.folvark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @GetMapping("/contact")
    public String pageArchportal(){

        return "contact-page";
    }

}
