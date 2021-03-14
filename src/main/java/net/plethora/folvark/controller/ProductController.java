package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@Controller
public class ProductController {

    private final DaoProductMap daoProductMap;
    private final AuthService authService;

    public ProductController(DaoProductMap daoProductMap, AuthService authService) {
        this.daoProductMap = daoProductMap;
        this.authService = authService;
    }

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable(value = "id") String id, HttpSession httpSession, Model model) {
        authService.checkCart(httpSession);
        authService.viewUserAccount(model);
        int countProduct = authService.countProduct(httpSession);
        ProductMap product = daoProductMap.findById(id);
        model.addAttribute("product", product);
        model.addAttribute("countProducts", countProduct);
        return "product-page";

    }
}