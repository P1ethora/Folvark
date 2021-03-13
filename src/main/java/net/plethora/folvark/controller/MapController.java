package net.plethora.folvark.controller;

import net.plethora.folvark.models.CheckedCartProduct;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.service.AuthService;
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
    private final AuthService authService;

    public MapController(PagerService pagerService, ProductService productService, AuthService authService) {
        this.pagerService = pagerService;
        this.authService = authService;
        this.productService = productService;
    }

    @GetMapping("/maps")
//    @PreAuthorize("hasAuthority('developers:read')")
    public String startMap(@RequestParam(required = false) String sort, @RequestParam(required = false) String page, HttpSession httpSession, Model model) {

        authService.checkCart(httpSession);
        int countProduct = authService.countProduct(httpSession);

        List<ProductMap> products = productService.fillingProductList(sort, page, pagerService);
        List<CheckedCartProduct> checkedCartProducts = authService.checkProductForCart(products, httpSession);
        ;

        model.addAttribute("nameUser", authService.getAuthUser().getFirstName() + " " + authService.getAuthUser().getLastName());
        model.addAttribute("countPage", pagerService.getArrayPage(productService.getCountProduct()));
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("products", checkedCartProducts);
        model.addAttribute("header", "Карты");
        model.addAttribute("countProduct", productService.getCountProduct());
        model.addAttribute("sort", sort);
        model.addAttribute("countProducts", countProduct);

        if (page != null) {
            model.addAttribute("pageN", "'?page='" + page);
        } else {
            model.addAttribute("pageN", "");
        }

        return "map-page";
    }

    @GetMapping("/maps/{category}")
//    @PreAuthorize("hasAuthority('developers:read')")
    public String categoryMap(@PathVariable("category") String category, @RequestParam(required = false) String sort, @RequestParam(required = false) String page, HttpSession httpSession, Model model) {

        authService.checkCart(httpSession);
        int countProduct = authService.countProduct(httpSession);

        List<ProductMap> products = productService.fillingProductListByCategory(sort, page, pagerService, productService, category);

        int[] countPage = pagerService.getArrayPage(productService.getCountProduct(category));
        if (countPage.length > 1) {
            model.addAttribute("countPage", countPage);
        }

        model.addAttribute("nameUser", authService.getAuthUser().getFirstName() + " " + authService.getAuthUser().getLastName());
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("products", products);
        model.addAttribute("header", productService.getNameCategory(category));
        model.addAttribute("category", category);
        model.addAttribute("countProduct", productService.getCountProduct(category));
        model.addAttribute("sort", sort);
        model.addAttribute("countProducts", countProduct);
        if (page != null) {
            model.addAttribute("pageN", "'?page='" + page);
        } else {
            model.addAttribute("pageN", "");
        }

        return "map-page";
    }

    @PostMapping("/maps/addToCart")
    public @ResponseBody
    void addNewWorker(@RequestBody String jsonString, HttpSession httpSession) {
        authService.checkCart(httpSession);
        String idProduct = jsonString.replace("\"", "");
        authService.addProductToCart(idProduct, httpSession);
    }
}