package net.plethora.folvark.dao;

import net.plethora.folvark.dao.repo.CartRepository;
import net.plethora.folvark.models.Cart;
import org.springframework.stereotype.Component;

@Component
public class DaoCart {

    private CartRepository cartRepository;

    public DaoCart(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart findCart(String id) {
        return cartRepository.findById(id).orElse(null);
    }

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void editCart(Cart cart) {
        saveCart(cart);
    }

}
