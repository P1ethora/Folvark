package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.service.PagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ProductController {

    @Autowired
    private DaoProductMap daoProductMap;

    @GetMapping("/product/{id}")
    public String viewProduct(@PathVariable(value = "id") String id, Model model) {
        ProductMap product = daoProductMap.findById(id);
        model.addAttribute("product", product);
        return "product-page";

    }
}
