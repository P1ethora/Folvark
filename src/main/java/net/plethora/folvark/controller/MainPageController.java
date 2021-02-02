package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPageController {

    private ProductService productService;

    public MainPageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String getMain(Model model) {

        model.addAttribute("products", productService.getLastProducts());

        return "main-page";
    }

}
