package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.ProductMapCategory;
import net.plethora.folvark.service.CartService;
import net.plethora.folvark.service.ProductService;
import net.plethora.folvark.service.SessionOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainPageController {

    private final ProductService productService;
    private final CartService cartService;
    private final SessionOperationService sessionOperationService;

    public MainPageController(ProductService productService, CartService cartService, SessionOperationService sessionOperationService) {
        this.productService = productService;
        this.cartService = cartService;
        this.sessionOperationService = sessionOperationService;
    }

    @GetMapping("/")
    public String getMain(HttpSession httpSession, Model model) {
        sessionOperationService.checkCart(httpSession);
        int countProduct = cartService.getCountProduct((String) httpSession.getAttribute("idCart"));
        List<ProductMapCategory> productMapCategories = productService.getCategories();

        model.addAttribute("products", productService.getLastProducts());
        model.addAttribute("countProducts", countProduct);
        model.addAttribute("categories", productMapCategories);

        return "main-page";
    }

}