package net.plethora.folvark.controller;

import net.plethora.folvark.models.CartPackage;
import net.plethora.folvark.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    private final AuthService authService;

    public CartController(AuthService authService) {

        this.authService = authService;
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession httpSession, Model model) {
        int countProduct = authService.countProduct(httpSession);

        CartPackage cartPackage = authService.getCartPackage(httpSession);

        model.addAttribute("products", cartPackage.getProductMaps());
        model.addAttribute("allPrice", cartPackage.getAllPrice());
        model.addAttribute("countProducts", countProduct);
        return "cart-page";
    }

    @GetMapping("/cart/{id}/remove")
    public String removeItem(@PathVariable("id") String id, HttpSession httpSession, Model model) {
        authService.checkCart(httpSession);
        authService.remoteProduct(httpSession, id);

        return viewCart(httpSession, model);
    }

}