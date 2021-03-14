package net.plethora.folvark.controller;

import net.plethora.folvark.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ContactController {

    private final AuthService authService;

    public ContactController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/contact")
//    @PreAuthorize( "hasAuthority('developers:read')")
    public String pageArchportal(HttpSession httpSession, Model model) {
        authService.checkCart(httpSession);
        authService.viewUserAccount(model);
        int countProduct = authService.countProduct(httpSession);
        model.addAttribute("countProducts", countProduct);
        return "contact-page";
    }
}