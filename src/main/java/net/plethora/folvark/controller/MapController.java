package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.dao.DaoProductMapCategory;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.ProductMapCategory;
import net.plethora.folvark.service.PagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
public class MapController {

    @Autowired
    private DaoProductMap daoProductMap;
    @Autowired
    private PagerService pagerService;
    @Autowired
    private DaoProductMapCategory daoProductMapCategory;

    @GetMapping("/maps")
    public String startMap(Model model) {
        List<ProductMapCategory> categories = daoProductMapCategory.findAll();
        List<ProductMap> products = daoProductMap.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("header", "Карты");
        return "map-page";
    }

    @GetMapping("/maps/{category}")
    public String categoryMap(@PathVariable("category") String category, Model model) {
        List<ProductMapCategory> categories = daoProductMapCategory.findAll();

        String nameCategory = categories.stream()
                .filter(i -> i.getUrlNameCategory().equals(category))
                .findFirst().orElse(null)
                .getNameCategory();

        List<ProductMap> products = daoProductMap.findAllByCategory(nameCategory);

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("header", nameCategory);
        return "map-page";
    }


}
