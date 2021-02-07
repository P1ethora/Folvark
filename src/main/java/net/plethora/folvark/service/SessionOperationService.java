package net.plethora.folvark.service;

import net.plethora.folvark.dao.DaoCart;
import net.plethora.folvark.models.Cart;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionOperationService {

    private final CartService cartService;
    private final DaoCart daoCart;

    public SessionOperationService(CartService cartService, DaoCart daoCart) {
        this.cartService = cartService;
        this.daoCart = daoCart;
    }

    public void checkCart(HttpSession httpSession) {

        if (httpSession.getAttribute("idCart") == null || daoCart.findCart((String) httpSession.getAttribute("idCart")) == null) {
            Cart cart = new Cart();
            cartService.SaveCart(cart);
            httpSession.setAttribute("idCart", cart.getId());
        }
    }
}