package net.plethora.folvark.service;

import net.plethora.folvark.dao.DaoCart;
import net.plethora.folvark.dao.DaoUser;
import net.plethora.folvark.dao.repo.UserRepository;
import net.plethora.folvark.models.Cart;
import net.plethora.folvark.models.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionOperationService {

    private final CartService cartService;
    private final DaoCart daoCart;
    private final UserRepository userRepository;

    public SessionOperationService(CartService cartService, DaoCart daoCart, UserRepository userRepository) {
        this.cartService = cartService;
        this.daoCart = daoCart;
        this.userRepository = userRepository;
    }

    public void checkCart(HttpSession httpSession) {

        if (httpSession.getAttribute("idCart") == null || daoCart.findCart((String) httpSession.getAttribute("idCart")) == null) {
            Cart cart = new Cart();
            cartService.CreateCart(cart);
            httpSession.setAttribute("idCart", cart.getId());
        }
    }

    public void checkCart(String idCart, User user) {

        if (daoCart.findCart(idCart) == null) {
            Cart cart = new Cart();
            cartService.CreateCart(cart);
            user.setIdCart(cart.getId());
            userRepository.save(user);
        }
    }

}