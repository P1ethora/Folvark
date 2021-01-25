package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.ProductMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MapController {

    @Autowired
    private DaoProductMap daoProductMap;

    @GetMapping("/maps")
    public String pageMap(Model model) {
        List<ProductMap> products = daoProductMap.findAll();
        model.addAttribute("products", products);
        return "map-page";
    }


}
