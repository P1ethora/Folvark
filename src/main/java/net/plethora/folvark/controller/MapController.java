package net.plethora.folvark.controller;

import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.service.CartService;
import net.plethora.folvark.service.PagerService;
import net.plethora.folvark.service.ProductService;
import net.plethora.folvark.service.SessionOperationService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MapController {

    private final PagerService pagerService;
    private final ProductService productService;
    private final CartService cartService;
    private final SessionOperationService sessionOperationService;

    public MapController(PagerService pagerService, ProductService productService, CartService cartService,
                         SessionOperationService sessionOperationService) {
        this.sessionOperationService = sessionOperationService;
        this.pagerService = pagerService;
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/maps")
    public String startMap(@RequestParam(required = false) String sort, @RequestParam(required = false) String page, HttpSession httpSession, Model model) {
        sessionOperationService.checkCart(httpSession);
        int countProduct = cartService.getCountProduct((String) httpSession.getAttribute("idCart"));

        List<ProductMap> products = productService.fillingProductList(sort, page, pagerService);
        model.addAttribute("countPage", pagerService.getArrayPage(productService.getCountProduct()));
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("products", products);
        model.addAttribute("header", "Карты");
        model.addAttribute("countProduct", productService.getCountProduct());
        model.addAttribute("sort", sort);
        model.addAttribute("countProducts", countProduct);

        return "map-page";
    }

    @GetMapping("/maps/{category}")
    public String categoryMap(@PathVariable("category") String category, @RequestParam(required = false) String sort, @RequestParam(required = false) String page, HttpSession httpSession, Model model) {
        sessionOperationService.checkCart(httpSession);
        List<ProductMap> products = productService.fillingProductListByCategory(sort, page, pagerService, productService, category);
        int countProduct = cartService.getCountProduct((String) httpSession.getAttribute("idCart"));

        model.addAttribute("countPage", pagerService.getArrayPage(productService.getCountProduct(category)));
        model.addAttribute("categories", productService.getCategories());
        model.addAttribute("products", products);
        model.addAttribute("header", productService.getNameCategory(category));
        model.addAttribute("category", category);
        model.addAttribute("countProduct", productService.getCountProduct(category));
        model.addAttribute("sort", sort);
        model.addAttribute("countProducts", countProduct);
        return "map-page";
    }

    @PostMapping("/maps/addToCart")
    public @ResponseBody
    void addNewWorker(@RequestBody String jsonString, HttpSession httpSession) {
        sessionOperationService.checkCart(httpSession);
        String idCart = (String) httpSession.getAttribute("idCart");
        String idProduct = jsonString.replace("\"", "");
        cartService.addProduct(idProduct, idCart);

    }

}