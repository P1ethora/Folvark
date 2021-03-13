package net.plethora.folvark.service;

import net.plethora.folvark.dao.DaoCart;
import net.plethora.folvark.models.Cart;
import net.plethora.folvark.models.CheckedCartProduct;
import net.plethora.folvark.models.ProductMap;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CartService {

    private DaoCart daoCart;

    public CartService(DaoCart daoCart) {
        this.daoCart = daoCart;
    }

    public void CreateCart(Cart cart) {
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

    public int getCountProduct(Cart cart) {
        int count = 0;
        if (cart.getIdMaps() != null)
            count = cart.getIdMaps().length;

        return count;
    }

    public void removeProduct(String idCart, String idItem) {
        Cart cart = daoCart.findCart(idCart);
        cart.setIdMaps(ArrayUtils.removeElement(cart.getIdMaps(), idItem));
        daoCart.editCart(cart);
    }

    public Cart getCart(HttpSession httpSession) {
        return daoCart.findCart((String) httpSession.getAttribute("idCart"));
    }

    public Cart getCart(String idCart) {
        return daoCart.findCart(idCart);
    }

    public List<CheckedCartProduct> checkedCartProduct(List<ProductMap> list, Cart cart) {
        List<CheckedCartProduct> checkedCartProducts = new ArrayList<>();
        boolean ok = false;
        for (ProductMap productMap : list) {

            if (cart.getIdMaps() != null && cart.getIdMaps().length > 0) {
                for (String idProductInCart : cart.getIdMaps()) {
                    if (productMap.getId().equals(idProductInCart)) {
                        ok = true;
                        checkedCartProducts.add(new CheckedCartProduct(productMap, true));
                        break;
                    }
                }
            }
            if (!ok) {
                checkedCartProducts.add(new CheckedCartProduct(productMap, false));
            }
            ok = false;
        }

        return checkedCartProducts;
    }
}