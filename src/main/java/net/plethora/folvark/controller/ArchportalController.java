package net.plethora.folvark.controller;

import net.plethora.folvark.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ArchportalController {

    private CartService cartService;

    public ArchportalController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/archportal")
    public String pageArchportal(HttpSession httpSession, Model model) {
        int countProduct = cartService.getCountProduct((String) httpSession.getAttribute("idCart"));
        model.addAttribute("countProducts", countProduct);
        return "archportal-page";
    }

}
