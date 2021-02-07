package net.plethora.folvark.service;

import net.plethora.folvark.dao.DaoCart;
import net.plethora.folvark.models.Cart;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CartService {

    private DaoCart daoCart;

    public CartService(DaoCart daoCart) {
        this.daoCart = daoCart;
    }

    public void SaveCart(Cart cart) {
        daoCart.saveCart(cart);
    }

    public void addProduct(String idProduct, String idCart) {

        Cart cart = daoCart.findCart(idCart);

        if (cart.getIdMaps() == null || cart.getIdMaps().length == 0) {
            cart.setIdMaps(new String[1]);
        } else {
            cart.setIdMaps(Arrays.copyOf(cart.getIdMaps(), cart.getIdMaps().length + 1));
        }

        cart.getIdMaps()[cart.getIdMaps().length - 1] = idProduct;
        daoCart.editCart(cart);
    }

    public int getCountProduct(String idCart) {
        Cart cart = daoCart.findCart(idCart);
        int count = 0;
        if (cart.getIdMaps() != null)
            count = cart.getIdMaps().length;

        return count;
    }

}