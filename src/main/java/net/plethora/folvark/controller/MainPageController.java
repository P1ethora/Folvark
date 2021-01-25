package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.ProductMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private DaoProductMap daoProductMap;

    @GetMapping("/")
    public String getMain(Model model) {

        List<ProductMap> products = daoProductMap.findAll();
        if(products.size()>10){
            int number = products.size()-11;
            products.removeIf(i->products.indexOf(i)<number);
        }

        model.addAttribute("products", products);

        return "main-page";
    }

}
