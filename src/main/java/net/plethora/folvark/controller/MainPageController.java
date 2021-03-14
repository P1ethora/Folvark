package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoEmailUser;
import net.plethora.folvark.models.EmailUser;
import net.plethora.folvark.models.ProductMapCategory;
import net.plethora.folvark.service.AuthService;
import net.plethora.folvark.service.ProductService;
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
    private final AuthService authService;
    private final DaoEmailUser daoEmailUser;

    public MainPageController(ProductService productService, AuthService authService,
                              DaoEmailUser daoEmailUser) {
        this.authService = authService;
        this.productService = productService;
        this.daoEmailUser = daoEmailUser;
    }

    @GetMapping("/")
//    @PreAuthorize( "hasAuthority('developers:read')")
    public String getMain(HttpSession httpSession, Model model) {
        authService.checkCart(httpSession);
        authService.viewUserAccount(model);
        int countProduct = authService.countProduct(httpSession);
        List<ProductMapCategory> productMapCategories = productService.getCategories();

        model.addAttribute("products", productService.getLastProducts());
        model.addAttribute("countProducts", countProduct);
        model.addAttribute("categories", productMapCategories);

        return "main-page";
    }

    @PostMapping("/addEmail")
//    @PreAuthorize( "hasAuthority('developers:write')")
    public @ResponseBody
    void addEmail(@RequestBody String eml) {
        String email = eml.replace("\"", "");
        daoEmailUser.saveEmail(new EmailUser(email));
    }
}