package net.plethora.folvark.service;

import lombok.AllArgsConstructor;
import net.plethora.folvark.dao.DaoCart;
import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.dao.repo.UserRepository;
import net.plethora.folvark.models.BagMap;
import net.plethora.folvark.models.Cart;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.User;
import net.plethora.folvark.models.state.ProductState;
import net.plethora.folvark.models.system.CartPackage;
import net.plethora.folvark.models.system.CheckedCartProduct;
import net.plethora.folvark.models.system.FavoritesPack;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CartService {

    private final DaoCart daoCart;
    private final UserRepository userRepository;
    private final DaoProductMap daoProductMap;
    private final ProductService productService;

    public void SaveCart(Cart cart) {
        daoCart.saveCart(cart);
    }

    void addProductToCart(String idProduct, Cart cart) {

        if (cart.getIdMaps() == null || cart.getIdMaps().size() == 0) {
            cart.setIdMaps(new ArrayList<>());
        }
        cart.getIdMaps().add(idProduct);
        daoCart.editCart(cart);
    }

    void removeProduct(String idCart, String idItem) {
        Cart cart = daoCart.findCart(idCart);
        cart.getIdMaps().remove(idItem);
        daoCart.editCart(cart);
    }

    Cart getCart(HttpSession httpSession) {
        return daoCart.findCart((String) httpSession.getAttribute("idCart"));
    }

    Cart getCart(String idCart) {
        return daoCart.findCart(idCart);
    }

    public List<CheckedCartProduct> checkProductForCart(List<ProductMap> list, Cart cart, FavoritesPack favoritesPack, BagMap bagMap) {

        List<CheckedCartProduct> checkedCartProducts = new ArrayList<>();

        for (ProductMap productMap : list) {
            CheckedCartProduct checkedCartProduct = new CheckedCartProduct();

            if (bagMap != null) {
                if (bagMap.getMaps() == null) {
                    bagMap.setMaps(new ArrayList<>());
                }
            }
            if (favoritesPack != null) {
                if (favoritesPack.getIdFavorites() == null) {
                    favoritesPack.setIdFavorites(new ArrayList<>());
                }
            }
            if (cart.getIdMaps() == null) {
                cart.setIdMaps(new ArrayList<>());
            }

            if (bagMap != null && bagMap.getMaps().stream().filter(p -> p.equals(productMap.getId())).findFirst().orElse(null) != null) {
                checkedCartProduct.setProductState(ProductState.BOUGHT);
            } else {

                checkCartAndFavorite(productMap, cart, favoritesPack, checkedCartProduct);

            }

            if (checkedCartProduct.getProductState() == null) {
                checkedCartProduct.setProductState(ProductState.TRADE);
            }

            checkedCartProduct.setProductMap(productMap);
            checkedCartProducts.add(checkedCartProduct);

        }

        return checkedCartProducts;
    }

    public List<CheckedCartProduct> getFavoriteMap(Iterable<ProductMap> list, Cart cart, FavoritesPack favoritesPack) {

        List<CheckedCartProduct> checkedCartProducts = new ArrayList<>();
        for (ProductMap productMap : list) {
            CheckedCartProduct checkedCartProduct = new CheckedCartProduct();

            if (favoritesPack != null) {
                if (favoritesPack.getIdFavorites() == null) {
                    favoritesPack.setIdFavorites(new ArrayList<>());
                }
            }
            if (cart.getIdMaps() == null) {
                cart.setIdMaps(new ArrayList<>());
            }

            checkCartAndFavorite(productMap, cart, favoritesPack, checkedCartProduct);

            checkedCartProduct.setProductMap(productMap);
            checkedCartProducts.add(checkedCartProduct);

        }

        return checkedCartProducts;
    }

    private void checkCartAndFavorite(ProductMap productMap, Cart cart, FavoritesPack favoritesPack, CheckedCartProduct checkedCartProduct) {
        if (cart.getIdMaps().stream().filter(p -> p.equals(productMap.getId())).findFirst().orElse(null) != null) {
            checkedCartProduct.setProductState(ProductState.CART);
        }

        if (favoritesPack != null && favoritesPack.getIdFavorites().stream().filter(p -> p.equals(productMap.getId())).findFirst().orElse(null) != null) {
            if (checkedCartProduct.getProductState() == ProductState.CART) {
                checkedCartProduct.setProductState(ProductState.CART_AND_FAVORITE);
            } else {
                checkedCartProduct.setProductState(ProductState.FAVORITE);
            }
        }
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
            cart.setIdMaps(new ArrayList<>());
            SaveCart(cart);
            httpSession.setAttribute("idCart", cart.getId());
        }
    }

    private void checkAvailabilityCart(User user) {
        Cart cart = new Cart();
        cart.setIdMaps(new ArrayList<>());
        SaveCart(cart);
        user.setIdCart(cart.getId());
        userRepository.save(user);
    }

    CartPackage getCartPackage(Cart cart) {
        List<ProductMap> productMaps = new ArrayList<>();
        double allPrice = 0;

        if (cart.getIdMaps().size() > 0) {
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