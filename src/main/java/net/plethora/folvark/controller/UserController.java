package net.plethora.folvark.controller;

import net.plethora.folvark.service.PersonaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final PersonaService personaService;

    public UserController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
model.addAttribute("elements", personaService.getElementsProfile());
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