package net.plethora.folvark.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import net.plethora.folvark.dao.DaoCart;
import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.Cart;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.service.CartService;
import net.plethora.folvark.service.SessionOperationService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CartController {

    private final DaoCart daoCart;
    private final SessionOperationService sessionOperationService;
    private final DaoProductMap daoProductMap;
    private final CartService cartService;

    public CartController(DaoCart daoCart, SessionOperationService sessionOperationService, DaoProductMap daoProductMap,
                          CartService cartService) {
        this.daoCart = daoCart;
        this.sessionOperationService = sessionOperationService;
        this.daoProductMap = daoProductMap;
        this.cartService = cartService;
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession httpSession, Model model) {
        int AllPrice = 0;
        sessionOperationService.checkCart(httpSession);
        Cart cart = daoCart.findCart((String) httpSession.getAttribute("idCart"));
        int countProduct;
        List<ProductMap> list = new ArrayList<>();
        if (cart != null && cart.getIdMaps() != null && (cart.getIdMaps().length > 0)) {
            countProduct = cart.getIdMaps().length;
            for (String id : cart.getIdMaps()) {
                ProductMap productMap = daoProductMap.findById(id);
                if (productMap != null) {
                    AllPrice += Integer.parseInt(productMap.getPrice());
                    list.add(productMap);
                }
            }

        } else {
            countProduct = 0;
        }
        model.addAttribute("products", list);
        model.addAttribute("allPrice", AllPrice);
        model.addAttribute("countProducts", countProduct);
        return "cart-page";
    }

    @GetMapping("/cart/{id}/remove")
    public String removeItem(@PathVariable("id") String id, HttpSession httpSession, Model model) {
        sessionOperationService.checkCart(httpSession);
        cartService.removeProduct((String) httpSession.getAttribute("idCart"), id);


        return viewCart(httpSession, model);
    }

}
