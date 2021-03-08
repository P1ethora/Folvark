package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoEmailUser;
import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.EmailUser;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.ProductMapCategory;
import net.plethora.folvark.service.CartService;
import net.plethora.folvark.service.ProductService;
import net.plethora.folvark.service.SessionOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainPageController {

    private final ProductService productService;
    private final CartService cartService;
    private final SessionOperationService sessionOperationService;
    private final DaoEmailUser daoEmailUser;

    public MainPageController(ProductService productService, CartService cartService, SessionOperationService sessionOperationService,
                              DaoEmailUser daoEmailUser) {
        this.productService = productService;
        this.cartService = cartService;
        this.sessionOperationService = sessionOperationService;
        this.daoEmailUser = daoEmailUser;
    }

    @GetMapping("/")
    public String getMain(HttpSession httpSession, Model model) {
        sessionOperationService.checkCart(httpSession);
        int countProduct = cartService.getCountProduct(cartService.getCart(httpSession));
        List<ProductMapCategory> productMapCategories = productService.getCategories();

        model.addAttribute("products", productService.getLastProducts());
        model.addAttribute("countProducts", countProduct);
        model.addAttribute("categories", productMapCategories);

        return "main-page";
    }

    @PostMapping("/addEmail")
    public @ResponseBody
    void addEmail(@RequestBody String eml) {
        String email = eml.replace("\"", "");
        daoEmailUser.saveEmail(new EmailUser(email));
    }

}