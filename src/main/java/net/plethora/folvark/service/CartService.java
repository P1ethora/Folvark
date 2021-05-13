package net.plethora.folvark.service;

import net.plethora.folvark.dao.DaoCart;
import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.dao.repo.UserRepository;
import net.plethora.folvark.models.*;
import net.plethora.folvark.models.system.CartPackage;
import net.plethora.folvark.models.system.CheckedCartProduct;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CartService {

    private DaoCart daoCart;
    private UserRepository userRepository;
    private DaoProductMap daoProductMap;

    public CartService(DaoCart daoCart, UserRepository userRepository, DaoProductMap daoProductMap) {
        this.daoCart = daoCart;
        this.userRepository = userRepository;
        this.daoProductMap = daoProductMap;
    }

    public void SaveCart(Cart cart) {
        daoCart.saveCart(cart);
    }

    void addProductToCart(String idProduct, Cart cart) {

        if (cart.getIdMaps() == null || cart.getIdMaps().length == 0) {
            cart.setIdMaps(new String[1]);
        } else {
            cart.setIdMaps(Arrays.copyOf(cart.getIdMaps(), cart.getIdMaps().length + 1));
        }

        cart.getIdMaps()[cart.getIdMaps().length - 1] = idProduct;
        daoCart.editCart(cart);
    }

    void removeProduct(String idCart, String idItem) {
        Cart cart = daoCart.findCart(idCart);
        cart.setIdMaps(ArrayUtils.removeElement(cart.getIdMaps(), idItem));
        daoCart.editCart(cart);
    }

    Cart getCart(HttpSession httpSession) {
        return daoCart.findCart((String) httpSession.getAttribute("idCart"));
    }

    Cart getCart(String idCart) {
        return daoCart.findCart(idCart);
    }

    /**
     * Green highlight product if it's in the shopping cart
     *
     * @param list products db
     * @param cart user or session
     * @return proven products
     */
    List<CheckedCartProduct> checkProductForCart(List<ProductMap> list, Cart cart) {
        List<CheckedCartProduct> checkedCartProducts = new ArrayList<>();
        boolean ok = false;

        if (cart.getIdMaps() == null || cart.getIdMaps().length <= 0) {
            for (ProductMap productMap : list) {
                checkedCartProducts.add(new CheckedCartProduct(productMap, false));
            }
        } else {
            for (ProductMap productMap : list) {
                for (String idProductInCart : cart.getIdMaps()) {
                    if (productMap.getId().equals(idProductInCart)) {
                        ok = true;
                        checkedCartProducts.add(new CheckedCartProduct(productMap, true));
                        break;
                    }
                }
                if (!ok) {
                    checkedCartProducts.add(new CheckedCartProduct(productMap, false));
                }
                ok = false;
            }
        }

        return checkedCartProducts;
    }


    /**
     * Creating a shopping cart if missing
     * Two parameters are accepted which are checked
     *
     * @param httpSession if not logged in
     * @param user        if the request is made by an authorized user
     */
    void checkAvailabilityCart(HttpSession httpSession, User user) {
        if (user == null) {
            if (httpSession.getAttribute("idCart") == null || daoCart.findCart((String) httpSession.getAttribute("idCart")) == null) {
                checkAvailabilityCart(httpSession);
            }
        } else {
            if (daoCart.findCart(user.getIdCart()) == null) {
                checkAvailabilityCart(user);
            }
        }
    }

    private void checkAvailabilityCart(HttpSession httpSession) {
        if (httpSession.getAttribute("idCart") == null || daoCart.findCart((String) httpSession.getAttribute("idCart")) == null) {
            Cart cart = new Cart();
            cart.setIdMaps(new String[0]);
            SaveCart(cart);
            httpSession.setAttribute("idCart", cart.getId());
        }
    }

    private void checkAvailabilityCart(User user) {
        Cart cart = new Cart();
        cart.setIdMaps(new String[0]);
        SaveCart(cart);
        user.setIdCart(cart.getId());
        userRepository.save(user);
    }

    CartPackage getCartPackage(Cart cart) {
        List<ProductMap> productMaps = new ArrayList<>();
        double allPrice = 0;

        if (cart.getIdMaps().length > 0) {
            for (String id : cart.getIdMaps()) {
                ProductMap productMap = daoProductMap.findById(id);
                if (productMap != null) {
                    allPrice += Integer.parseInt(productMap.getPrice());
                    productMaps.add(productMap);
                }
            }
        }
        return new CartPackage(productMaps, allPrice);
    }

}