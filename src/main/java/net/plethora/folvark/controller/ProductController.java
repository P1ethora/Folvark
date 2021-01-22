package net.plethora.folvark.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/product")
    public String viewProduct(){
        return "product-page";
    }
}
