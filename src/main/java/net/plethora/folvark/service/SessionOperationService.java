package net.plethora.folvark.service;

import net.plethora.folvark.models.Cart;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionOperationService {

    private CartService cartService;

    public SessionOperationService(CartService cartService) {
        this.cartService = cartService;
    }

    public void checkCart(HttpSession httpSession) {

        if (httpSession.getAttribute("idCart") == null) {
            Cart cart = new Cart();
            cartService.SaveCart(cart);
            httpSession.setAttribute("idCart", cart.getId());
        }
    }
}