package net.plethora.folvark.controller;

import net.plethora.folvark.service.CartService;
import net.plethora.folvark.service.SessionOperationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ArchportalController {

    private final CartService cartService;
    private final SessionOperationService sessionOperationService;

    public ArchportalController(CartService cartService, SessionOperationService sessionOperationService) {
        this.cartService = cartService;
        this.sessionOperationService = sessionOperationService;
    }

    @GetMapping("/archportal")
    public String pageArchportal(HttpSession httpSession, Model model) {
        sessionOperationService.checkCart(httpSession);
        int countProduct = cartService.getCountProduct((String) httpSession.getAttribute("idCart"));
        model.addAttribute("countProducts", countProduct);
        return "archportal-page";
    }

}
