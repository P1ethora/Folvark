package net.plethora.folvark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/register")
    public String openRegister() {

        return "registration-page";
    }

    @GetMapping("/recover")
    public String openRecover() {

        return "recover-page";
    }

}
