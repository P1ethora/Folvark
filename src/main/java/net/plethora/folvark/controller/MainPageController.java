package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.service.CartService;
import net.plethora.folvark.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainPageController {

    private final ProductService productService;
    private final CartService cartService;

    public MainPageController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/")
    public String getMain(HttpSession httpSession, Model model) {

        int countProduct = cartService.getCountProduct((String) httpSession.getAttribute("idCart"));

        model.addAttribute("products", productService.getLastProducts());
        model.addAttribute("countProducts", countProduct);

        return "main-page";
    }

}