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

//    @GetMapping("/maps?page={parameter}")
//    public String startMapParam(@PathVariable("parameter")String parameter, Model model) {
//        List<ProductMapCategory> categories = daoProductMapCategory.findAll();
//        List<ProductMap> products;
//
//        long countProduct = daoProductMap.getCount();
//        int countPage = (int)(countProduct / 5) + 1;
//        int[] arrPage = new int[countPage];
//        for(int i= 0;i<arrPage.length;i++){
//            arrPage[i]=i+1;
//        }
//
//        if(parameter.equals("all")){
//            products = pagerService.getAllProduct();
//        } else {
//products = pagerService.getAllProductByPage(Integer.parseInt(parameter)-1);
//        }
//
//        model.addAttribute("categories", categories);
//        model.addAttribute("products", products);
//        model.addAttribute("header", "Карты");
//        model.addAttribute("countProduct",countProduct);
//        return "map-page";
//    }
//
//    @GetMapping("/maps/{category}?sort={sort}?page={parameter}")
//    public String categoryMap(@PathVariable("category") String category, Model model) {
//        List<ProductMapCategory> categories = daoProductMapCategory.findAll();
//
//        String nameCategory = categories.stream()
//                .filter(i -> i.getUrlNameCategory().equals(category))
//                .findFirst().orElse(null)
//                .getNameCategory();
//
//        List<ProductMap> products = daoProductMap.findAllByCategory(nameCategory);
//
//        model.addAttribute("categories", categories);
//        model.addAttribute("products", products);
//        model.addAttribute("header", nameCategory);
//        return "map-page";
//    }


}
