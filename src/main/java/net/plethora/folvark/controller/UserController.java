package net.plethora.folvark.controller;

import lombok.AllArgsConstructor;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.User;
import net.plethora.folvark.service.AuthService;
import net.plethora.folvark.service.FavoritesService;
import net.plethora.folvark.service.PersonaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@AllArgsConstructor
@Controller
public class UserController {

    private final PersonaService personaService;
    private final AuthService authService;
    private final FavoritesService favoritesService;

    @GetMapping("/profile")
    public String getProfile(Model model) {

        User user = authService.getAuthUser();

        model.addAttribute("elements", personaService.getElementsProfile());
        model.addAttribute("nameElement", "Профиль");

        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("loginName", user.getLoginName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("middleName", user.getMiddleName());
        model.addAttribute("birthday", user.getBirthday());
        model.addAttribute("numberPhone", user.getNumberPhone());
        model.addAttribute("urlPhoto", user.getUrlPhoto());
        model.addAttribute("gender", user.getGender());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("about", "Любитель легких денег и искатель артефактов ушедшей эпохи");
        model.addAttribute("fieldFirstName", "Имя");
        model.addAttribute("fieldLoginName", "Логин");
        model.addAttribute("fieldLastName", "Фамилия");
        model.addAttribute("fieldMiddleName", "Отчество");
        model.addAttribute("fieldBirthday", "Дата рождения");
        model.addAttribute("fieldNumberPhone", "Номер телефона");
        model.addAttribute("fieldGender", "Пол");
        model.addAttribute("fieldEmail", "Электронная почта");
        model.addAttribute("fieldAbout", "О себе");
        return "profile";
    }

    @GetMapping("/coupons")
    public String getCoupons(Model model) {
        model.addAttribute("elements", personaService.getElementsProfile());
        return "coupons";
    }

    @GetMapping("/favorites")
    public String getFavorites(Model model) {
        User user = authService.getAuthUser();

        Iterable<ProductMap> productMaps = favoritesService.getFavoritesProduct(user.getIdFavoritesPack());
        long count = productMaps.spliterator().getExactSizeIfKnown();
        model.addAttribute("header", "Избранное");
        model.addAttribute("elements", personaService.getElementsProfile());
        model.addAttribute("products", productMaps);
        model.addAttribute("countProduct", count);
        return "favorites";
    }

    @PostMapping("/addFavorite")
    public @ResponseBody
    void addToFavorites(@RequestBody String idProduct) {
        String id = idProduct.replace("\"", "");
        User user = authService.getAuthUser();
        favoritesService.addProductToFavorites(id, user.getIdFavoritesPack());
    }

    @PostMapping("/deleteFavorite")
    public @ResponseBody
    void deleteFavorite(@RequestBody String idProduct) {
        String id = idProduct.replace("\"", "");
        User user = authService.getAuthUser();
        favoritesService.deleteProductToFavorites(id, user.getIdFavoritesPack());
    }

    @GetMapping("/messages")
    public String getMessages(Model model) {
        model.addAttribute("elements", personaService.getElementsProfile());
        return "messages";
    }

    @GetMapping("/my-comments")
    public String getMyComments(Model model) {
        model.addAttribute("elements", personaService.getElementsProfile());
        return "my-comments";
    }

    @GetMapping("/my-maps")
    public String getMyMaps(Model model) {
        String idBugMap = authService.getAuthUser().getIdBugMap();
        List<ProductMap> products = personaService.getPurchasedMap(idBugMap);

        model.addAttribute("elements", personaService.getElementsProfile());
        model.addAttribute("products", products);
        model.addAttribute("countProduct", products.size());
        return "my-maps";
    }

    @GetMapping("/requests")
    public String getRequests(Model model) {
        model.addAttribute("elements", personaService.getElementsProfile());
        return "requests";
    }

}