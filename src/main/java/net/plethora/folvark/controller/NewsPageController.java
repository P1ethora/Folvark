package net.plethora.folvark.controller;

import net.plethora.folvark.dao.DaoNewsPortal;
import net.plethora.folvark.models.PortalNews;
import net.plethora.folvark.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class NewsPageController {

    private final DaoNewsPortal daoNewsPortal;
    private final AuthService authService;

    public NewsPageController(DaoNewsPortal daoNewsPortal, AuthService authService) {
        this.daoNewsPortal = daoNewsPortal;
        this.authService = authService;
    }

    @GetMapping("/news/{id}")
    public String getNewsPage(@PathVariable("id") String id, HttpSession httpSession, Model model) {

        int countProduct = authService.countProduct(httpSession);
        authService.viewUserAccount(model);
        PortalNews news = daoNewsPortal.findById(id);

        model.addAttribute("news", news);
        model.addAttribute("countProduct", countProduct);
//        model.addAttribute("date", news.getDate());
//        model.addAttribute("title", news.getTitle());
//        model.addAttribute("title", news.getTitle());

        return "news-page";
    }
}