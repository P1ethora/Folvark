package net.plethora.folvark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String viewCart(HttpSession httpSession, Model model) {
        return "cart-page";
    }
}
