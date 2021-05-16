package net.plethora.folvark.controller;

import net.plethora.folvark.models.User;
import net.plethora.folvark.service.AuthService;
import net.plethora.folvark.service.PersonaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final PersonaService personaService;
    private final AuthService authService;

    public UserController(PersonaService personaService, AuthService authService) {
        this.personaService = personaService;
        this.authService = authService;
    }

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

        model.addAttribute("fieldFirstName", "Имя");
        model.addAttribute("fieldLoginName", "Логин");
        model.addAttribute("fieldLastName", "Фамилия");
        model.addAttribute("fieldMiddleName", "Отчество");
        model.addAttribute("fieldBirthday", "Дата рождения");
        model.addAttribute("fieldNumberPhone", "Номер телефона");
        model.addAttribute("fieldGender", "Пол");
        model.addAttribute("fieldEmail", "Электронная почта");
        return "profile";
    }

    @GetMapping("/coupons")
    public String getCoupons(Model model) {
        model.addAttribute("elements", personaService.getElementsProfile());
        return "coupons";
    }

    @GetMapping("/favorites")
    public String getFavorites(Model model) {
        model.addAttribute("elements", personaService.getElementsProfile());
        return "favorites";
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
        model.addAttribute("elements", personaService.getElementsProfile());
        return "my-maps";
    }

    @GetMapping("/requests")
    public String getRequests(Model model) {
        model.addAttribute("elements", personaService.getElementsProfile());
        return "requests";
    }

}