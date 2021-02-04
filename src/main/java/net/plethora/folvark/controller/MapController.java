package net.plethora.folvark.controller;

import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.service.PagerService;
import net.plethora.folvark.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MapController {

    private final PagerService pagerService;
    private final ProductService productService;

    public MapController(PagerService pagerService, ProductService productService) {
        this.pagerService = pagerService;
        this.productService = productService;
    }

    @GetMapping("/maps")
    public String startMap(@RequestParam(required = false) String sort, @RequestParam(required = false) String page, HttpSession httpSession, Model model) {

        List<ProductMap> products = productService.fillingProductList(sort, page, pagerService);
        model.addAttribute("countPage", pagerService.getArrayPage(productService.getCountProduct()));
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("products", products);
        model.addAttribute("header", "Карты");
        model.addAttribute("countProduct", productService.getCountProduct());
        model.addAttribute("sort", sort);

        return "map-page";
    }

    @GetMapping("/maps/{category}")
    public String categoryMap(@PathVariable("category") String category, @RequestParam(required = false) String sort, @RequestParam(required = false) String page, HttpSession httpSession, Model model) {

        List<ProductMap> products = productService.fillingProductListByCategory(sort, page, pagerService, productService, category);

        model.addAttribute("countPage", pagerService.getArrayPage(productService.getCountProduct(category)));
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("products", products);
        model.addAttribute("header", productService.getNameCategory(category));
        model.addAttribute("category", category);
        model.addAttribute("countProduct", productService.getCountProduct(category));
        model.addAttribute("sort", sort);

        return "map-page";
    }

    @PostMapping("/maps/addToCart")
    public @ResponseBody
    void addNewWorker(@RequestBody String jsonString, HttpSession httpSession) {
        System.out.println(jsonString.replace("\"", "") + " " + httpSession.getId());
        //do business logic
    }

}