package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoCart;
import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.Cart;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.service.SessionOperationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {

    private DaoCart daoCart;
    private SessionOperationService sessionOperationService;
    private DaoProductMap daoProductMap;

    public CartController(DaoCart daoCart, SessionOperationService sessionOperationService, DaoProductMap daoProductMap) {
        this.daoCart = daoCart;
        this.sessionOperationService = sessionOperationService;
        this.daoProductMap = daoProductMap;
    }

    @GetMapping("/cart")
    public String viewCart(HttpSession httpSession, Model model) {
        int AllPrice = 0;
        sessionOperationService.checkCart(httpSession);
        Cart cart = daoCart.findCart((String) httpSession.getAttribute("idCart"));
        List<ProductMap> list = new ArrayList<>();
        if (cart != null && cart.getIdMaps() != null && (cart.getIdMaps().length > 0)) {
            for (String id : cart.getIdMaps()) {
                ProductMap productMap = daoProductMap.findById(id);
                if (productMap != null) {
                    AllPrice += Integer.parseInt(productMap.getPrice());
                    list.add(productMap);
                }
            }

        }
        model.addAttribute("products", list);
        model.addAttribute("allPrice", AllPrice);
        return "cart-page";
    }
}
