package net.plethora.folvark.service;

import lombok.AllArgsConstructor;
import net.plethora.folvark.dao.repo.BagMapRepository;
import net.plethora.folvark.dao.repo.UserRepository;
import net.plethora.folvark.models.BagMap;
import net.plethora.folvark.models.Cart;
import net.plethora.folvark.models.system.CartPackage;
import net.plethora.folvark.models.system.CheckedCartProduct;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.User;
import net.plethora.folvark.models.system.FavoritesPack;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Methods of this class return values according to authentication
 */
@AllArgsConstructor
@Service
public class AuthService {

    private final CartService cartService;
    private final UserRepository userRepository;
    private final FavoritesService favoritesService;
    private final BagMapRepository bagMapRepository;

    public User getAuthUser() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(loggedInUser.getName()).orElse(null);
    }

    public void checkCart(HttpSession httpSession) {
        cartService.checkAvailabilityCart(httpSession, getAuthUser());
    }

    public int countProduct(HttpSession httpSession) {
        if (getAuthUser() == null) {
            return cartService.getCart(httpSession).getIdMaps().size();
        } else {
            List<String> idMaps = cartService.getCart(getAuthUser().getIdCart()).getIdMaps();
            if (cartService.getCart(getAuthUser().getIdCart()).getIdMaps() == null) {
                return 0;
            } else return idMaps.size();
        }
    }

    public void remoteProduct(HttpSession httpSession, String idProduct) {
        if (getAuthUser() == null) {
            cartService.removeProduct((String) httpSession.getAttribute("idCart"), idProduct);
        } else {
            cartService.removeProduct(getAuthUser().getIdCart(), idProduct);
        }
    }

//    public List<CheckedCartProduct> checkProductForCart(List<ProductMap> products, HttpSession httpSession) {
//
//        List<CheckedCartProduct> checkedCartProducts;
//
//        if (getAuthUser() == null) {
//            checkedCartProducts = cartService.checkProductForCart(products, cartService.getCart(httpSession));
//        } else {
//            checkedCartProducts = cartService.checkProductForCart(products, cartService.getCart(getAuthUser().getIdCart()));
//        }
//        return checkedCartProducts;
//    }

//TODO test
    public List<CheckedCartProduct> checkProduct(List<ProductMap> products, HttpSession httpSession) {

        List<CheckedCartProduct> checkedCartProducts;

        if (getAuthUser() == null) {
            checkedCartProducts = cartService.checkProductForCart(products,cartService.getCart(httpSession), null,null);
           // checkedCartProducts = cartService.checkProductForCart(products, cartService.getCart(httpSession));
        } else {
            User user = getAuthUser();
            Cart cart = cartService.getCart(user.getIdCart());
            FavoritesPack favoritesPack = favoritesService.getById(user.getIdFavoritesPack());
            BagMap bagMap = bagMapRepository.findById(user.getIdBugMap()).orElse(null);

            checkedCartProducts = cartService.checkProductForCart(products,cart, favoritesPack,bagMap);
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

    /**
     * Added user name for header
     *
     * @param model
     */
    public void viewUserAccount(Model model) {
        if (getAuthUser() != null) {
            User user = getAuthUser();
            String name = user.getFirstName() + " " + user.getLastName();
            model.addAttribute("nameUser", name);
        }
    }

    public void controlUser(HttpSession httpSession, Model model) {
        checkCart(httpSession);
        viewUserAccount(model);
    }

}