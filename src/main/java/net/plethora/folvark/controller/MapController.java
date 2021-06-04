package net.plethora.folvark.controller;

import lombok.AllArgsConstructor;
import net.plethora.folvark.models.system.CheckedCartProduct;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.service.AuthService;
import net.plethora.folvark.service.CartService;
import net.plethora.folvark.service.PagerService;
import net.plethora.folvark.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@AllArgsConstructor
@Controller
public class MapController {

    private final PagerService pagerService;
    private final ProductService productService;
    private final AuthService authService;
    private final CartService cartService;

    @GetMapping("/maps")
//    @PreAuthorize("hasAuthority('developers:read')")
    public String startMap(@RequestParam(required = false) String sort, @RequestParam(required = false) String page, HttpSession httpSession, Model model) {

        authService.controlUser(httpSession, model);

        List<ProductMap> products = productService.fillingProductList(sort, page, pagerService);
        attribute(httpSession, model, products, null, sort, page);

        return "map-page";
    }

    @GetMapping("/maps/{category}")
//    @PreAuthorize("hasAuthority('developers:read')")
    public String categoryMap(@PathVariable("category") String category, @RequestParam(required = false) String sort, @RequestParam(required = false) String page, HttpSession httpSession, Model model) {

        authService.controlUser(httpSession, model);

        List<ProductMap> products = productService.fillingProductListByCategory(sort, page, pagerService, productService, category);
        attribute(httpSession, model, products, category, sort, page);

        return "map-page";
    }

    private void attribute(HttpSession httpSession, Model model, List<ProductMap> products, String category, String sort, String page) {

        //List<CheckedCartProduct> checkedCartProducts = authService.checkProductForCart(products, httpSession);
        List<CheckedCartProduct> checkedCartProducts = authService.checkProduct(products,httpSession);
        int countProduct = authService.countProduct(httpSession);

        model.addAttribute("countProducts", countProduct);
        model.addAttribute("products", checkedCartProducts);
        model.addAttribute("sort", sort);

        if (category != null) {
            int[] countPage = pagerService.getArrayPage(productService.getCountProduct(category));
            if (countPage.length > 1) {
                model.addAttribute("countPage", countPage);
            }
            model.addAttribute("categories", productService.getCategories());
            model.addAttribute("header", productService.getNameCategory(category));
            model.addAttribute("category", category);
            model.addAttribute("countProduct", productService.getCountProduct(category));

        } else {
            model.addAttribute("countPage", pagerService.getArrayPage(productService.getCountProduct()));
            model.addAttribute("categories", productService.getCategories());
            model.addAttribute("header", "Карты");
            model.addAttribute("countProduct", productService.getCountProduct());
        }

        if (page != null) {
            model.addAttribute("pageN", "'?page='" + page);
        } else {
            model.addAttribute("pageN", "");
        }

    }
}