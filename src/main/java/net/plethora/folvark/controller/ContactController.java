package net.plethora.folvark.controller;

import net.plethora.folvark.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ContactController {

    private final CartService cartService;

    public ContactController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/contact")
    public String pageArchportal(HttpSession httpSession, Model model) {
        int countProduct = cartService.getCountProduct((String) httpSession.getAttribute("idCart"));
        model.addAttribute("countProducts", countProduct);
        return "contact-page";
    }

}
