package net.plethora.folvark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ContactController {

    @GetMapping("/contact")
    public String pageArchportal(HttpSession httpSession, Model model) {

        return "contact-page";
    }

}
