package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoUser;
import net.plethora.folvark.models.Cart;
import net.plethora.folvark.models.User;
import net.plethora.folvark.models.state.Role;
import net.plethora.folvark.models.state.Status;
import net.plethora.folvark.service.CartService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final DaoUser daoUser;
    private final PasswordEncoder passwordEncoder;
    private final CartService cartService;

    public LoginController(DaoUser daoUser, PasswordEncoder passwordEncoder, CartService cartService) {
        this.daoUser = daoUser;
        this.passwordEncoder = passwordEncoder;
        this.cartService = cartService;
    }

    @GetMapping("/register")
    public String openRegister() {

        return "registration-page";
    }

    @PostMapping("/register")
    public String register(@RequestParam String name, @RequestParam String lastName, @RequestParam String email,
                           @RequestParam String password, @RequestParam String confirmPassword, Model model) {

        if (daoUser.findByEmail(email) != null) {
            model.addAttribute("message-email", "Такой email уже используется");
            return "registration-page";
        } else {
            if (!password.equals(confirmPassword)) {
                model.addAttribute("message-password", "Пароли не совпадают");
                return "registration-page";
            } else {
                User user = new User();
                user.setEmail(email);
                user.setPassword(passwordEncoder.encode(password));
                user.setFirstName(name);
                user.setLastName(lastName);
                user.setRole(Role.USER);
                user.setStatus(Status.ACTIVE);
                Cart cart = new Cart();
                cartService.SaveCart(cart);
                user.setIdCart(cart.getId());
                daoUser.saveUser(user);
                return "redirect:/login";
            }
        }

    }

    @GetMapping("/recover")
    public String openRecover() {
        return "recover-page";
    }

    @GetMapping("/login")
    public String openCheckout() {
        return "login";
    }

}