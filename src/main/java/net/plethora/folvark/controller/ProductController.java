package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.service.CartService;
import net.plethora.folvark.service.SessionOperationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class ProductController {

    private final DaoProductMap daoProductMap;
    private final CartService cartService;
    private final SessionOperationService sessionOperationService;

    public ProductController(DaoProductMap daoProductMap, CartService cartService, SessionOperationService sessionOperationService) {
        this.daoProductMap = daoProductMap;
        this.cartService = cartService;
        this.sessionOperationService = sessionOperationService;
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable(value = "id") String id, HttpSession httpSession, Model model) {
        sessionOperationService.checkCart(httpSession);
        int countProduct = cartService.getCountProduct((String) httpSession.getAttribute("idCart"));
        ProductMap product = daoProductMap.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("countProducts", countProduct);
        return "product-page";

    }
}