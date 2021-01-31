package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.dao.DaoProductMapCategory;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.ProductMapCategory;
import net.plethora.folvark.service.PagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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

    public String startMap(@RequestParam(required = false) String sort, @RequestParam(required = false) String page, Model model) {
        List<ProductMapCategory> categories = daoProductMapCategory.findAll();
        long countProduct = daoProductMap.getCount();
        int countPage = (int) (countProduct / 5) + 1;
        int[] arrPage = new int[countPage];
        for (int i = 0; i < arrPage.length; i++) {
            arrPage[i] = i + 1;
        }
        List<ProductMap> products;
        if (sort == null) {
            if (page == null) {
                products = pagerService.getAllProductByPage(0);
            } else if (page.equals("all")) {
                products = pagerService.getAllProduct();
            } else {
                products = pagerService.getAllProductByPage(Integer.parseInt(page) - 1);
            }
        } else {
            if (page == null) {
                products = pagerService.getPageProductSort(0, sort);
            } else if (page.equals("all")) {
                products = pagerService.getAllProductSort(sort);
            } else {
                products = pagerService.getPageProductSort(Integer.parseInt(page) - 1, sort);
            }
        }

        model.addAttribute("countPage", arrPage);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("header", "Карты");
        model.addAttribute("countProduct", countProduct);
        model.addAttribute("sort", sort);


        return "map-page";
    }

    @GetMapping("/maps/{category}")
    public String categoryMap(@PathVariable("category") String category, @RequestParam(required = false) String sort, @RequestParam(required = false) String page, Model model) {
        List<ProductMapCategory> categories = daoProductMapCategory.findAll();

        String nameCategory = categories.stream()
                .filter(i -> i.getUrlNameCategory().equals(category))
                .findFirst().orElse(null)
                .getNameCategory();

        long countProduct = daoProductMap.getCount(nameCategory);
        int countPage = (int) (countProduct / 5) + 1;
        int[] arrPage = new int[countPage];
        for (int i = 0; i < arrPage.length; i++) {
            arrPage[i] = i + 1;
        }

        List<ProductMap> products;
        if (sort == null) {
            if (page == null) {
                products = pagerService.getProductByPageByCategory(nameCategory, 0);
            } else if (page.equals("all")) {
                products = pagerService.getAllProductByCategory(nameCategory);
            } else {
                products = pagerService.getProductByPageByCategory(nameCategory, Integer.parseInt(page) - 1);
            }
        } else {
            if (page == null) {
                products = pagerService.getProductByPageByCategorySort(nameCategory, 0, sort);
            } else if (page.equals("all")) {
                products = pagerService.getAllProductByCategorySort(nameCategory, sort);
            } else {
                products = pagerService.getProductByPageByCategorySort(nameCategory, Integer.parseInt(page) - 1, sort);
            }
        }

        model.addAttribute("countPage", arrPage);
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("header", nameCategory);
        model.addAttribute("category", category);
        model.addAttribute("countProduct", countProduct);
        model.addAttribute("sort", sort);
        return "map-page";
    }
}