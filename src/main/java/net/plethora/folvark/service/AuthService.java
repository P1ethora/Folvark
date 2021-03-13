package net.plethora.folvark.service;

import net.plethora.folvark.dao.repo.UserRepository;
import net.plethora.folvark.models.CartPackage;
import net.plethora.folvark.models.CheckedCartProduct;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Methods of this class return values according to authentication
 */
@Service
public class AuthService {

    private final CartService cartService;
    private final UserRepository userRepository;

    public AuthService(CartService cartService, UserRepository userRepository) {
        this.cartService = cartService;
        this.userRepository = userRepository;
    }

    public User getAuthUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(loggedInUser.getName()).orElse(null);
    }

    public void checkCart(HttpSession httpSession) {
        cartService.checkAvailabilityCart(httpSession, getAuthUser());
    }

    public int countProduct(HttpSession httpSession) {
        if (getAuthUser() == null) {
            return cartService.getCart(httpSession).getIdMaps().length;
        } else {
            return cartService.getCart(getAuthUser().getIdCart()).getIdMaps().length;
        }
    }

    public void remoteProduct(HttpSession httpSession, String idProduct) {
        if (getAuthUser() == null) {
            cartService.removeProduct((String) httpSession.getAttribute("idCart"), idProduct);
        } else {
            cartService.removeProduct(getAuthUser().getIdCart(), idProduct);
        }
    }

    public List<CheckedCartProduct> checkProductForCart(List<ProductMap> products, HttpSession httpSession) {

        List<CheckedCartProduct> checkedCartProducts;

        if (getAuthUser() == null) {
            checkedCartProducts = cartService.checkProductForCart(products, cartService.getCart(httpSession));
        } else {
            checkedCartProducts = cartService.checkProductForCart(products, cartService.getCart(getAuthUser().getIdCart()));
        }
        return checkedCartProducts;
    }

    public void addProductToCart(String idProduct, HttpSession httpSession) {

        if (getAuthUser() == null) {
            cartService.addProductToCart(idProduct, cartService.getCart(httpSession));
        } else {
            cartService.addProductToCart(idProduct, cartService.getCart(getAuthUser().getIdCart()));
        }
    }

    public CartPackage getCartPackage(HttpSession httpSession) {
        CartPackage cartPackage;
        if (getAuthUser() == null) {
            cartPackage = cartService.getCartPackage(cartService.getCart(httpSession));
        } else {
            cartPackage = cartService.getCartPackage(cartService.getCart(getAuthUser().getIdCart()));
        }
        return cartPackage;
    }
}