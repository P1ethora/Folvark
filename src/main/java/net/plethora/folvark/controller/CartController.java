package net.plethora.folvark.controller;

import net.plethora.folvark.models.state.ProductState;
import net.plethora.folvark.models.system.CartPackage;
import net.plethora.folvark.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    private final AuthService authService;

    public CartController(AuthService authService) {

        this.authService = authService;
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession httpSession, Model model) {
        int countProduct = authService.countProduct(httpSession);
        authService.viewUserAccount(model);
        CartPackage cartPackage = authService.getCartPackage(httpSession);

        model.addAttribute("products", cartPackage.getProductMaps());
        model.addAttribute("allPrice", cartPackage.getAllPrice());
        model.addAttribute("countProducts", countProduct);

        return "cart-page";
    }

    @GetMapping("/cart/{id}/remove")
    public String removeItem(@PathVariable("id") String id, HttpSession httpSession, Model model) {
        authService.controlUser(httpSession, model);
        authService.remoteProduct(httpSession, id);

        return viewCart(httpSession, model);
    }

    @PostMapping("/maps/addToCart")
    public @ResponseBody
    void addNewWorker(@RequestBody String jsonString, HttpSession httpSession) {
        authService.checkCart(httpSession);
        String idProduct = jsonString.replace("\"", "");
        authService.addProductToCart(idProduct, httpSession);
    }

//    @GetMapping("/persona/edit-profile")
//    public String editProfile() {
//
//        return "edit-profile-page";
//    }

}