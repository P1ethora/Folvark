package net.plethora.folvark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/persona")
    public String getPersona(Model model) {

        return "persona-page";
    }

}
