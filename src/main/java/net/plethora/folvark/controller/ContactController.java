package net.plethora.folvark.controller;

import net.plethora.folvark.service.CartService;
import net.plethora.folvark.service.SessionOperationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ContactController {

    private final CartService cartService;
    private final SessionOperationService sessionOperationService;

    public ContactController(CartService cartService, SessionOperationService sessionOperationService) {
        this.cartService = cartService;
        this.sessionOperationService = sessionOperationService;
    }

    @GetMapping("/contact")
    public String pageArchportal(HttpSession httpSession, Model model) {
        sessionOperationService.checkCart(httpSession);
        int countProduct = cartService.getCountProduct(cartService.getCart(httpSession));
        model.addAttribute("countProducts", countProduct);
        return "contact-page";
    }

}
