package net.plethora.folvark.controller;

import net.plethora.folvark.models.PortalNews;
import net.plethora.folvark.service.CartService;
import net.plethora.folvark.service.PagerService;
import net.plethora.folvark.service.PortalService;
import net.plethora.folvark.service.SessionOperationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArchportalController {

    private final CartService cartService;
    private final SessionOperationService sessionOperationService;
    private final PortalService portalService;
    private final PagerService pagerService;

    public ArchportalController(CartService cartService, PortalService portalService, SessionOperationService sessionOperationService,
                                PagerService pagerService) {
        this.cartService = cartService;
        this.sessionOperationService = sessionOperationService;
        this.portalService = portalService;
        this.pagerService = pagerService;
    }

    @GetMapping("/archportal")
    public String pageArchportal(@RequestParam(required = false) String sort, @RequestParam(required = false) String page, HttpSession httpSession, Model model) {
        sessionOperationService.checkCart(httpSession);
        int countProduct = cartService.getCountProduct(cartService.getCart(httpSession));
        model.addAttribute("countProducts", countProduct);

        List<PortalNews> portalNewsList = portalService.fillingPortalNewsList(sort, page, pagerService);
        model.addAttribute("countPage", pagerService.getArrayPage(portalService.getCountPortalNews()));
        model.addAttribute("categories", portalService.getCategories());
        model.addAttribute("portalNewsList", portalNewsList);
        model.addAttribute("header", "Карты");
        model.addAttribute("countPortalNews", portalService.getCountPortalNews());
        model.addAttribute("sort", sort);
        model.addAttribute("countProducts", countProduct);
        return "archportal-page";
    }

    @GetMapping("/archportal/{category}")
    public String archCategory(@RequestParam(required = false) String sort, @RequestParam(required = false) String page, @PathVariable("category") String category, HttpSession httpSession, Model model) {
        sessionOperationService.checkCart(httpSession);
        List<PortalNews> portalNewsList = portalService.fillingPortalNewsListByCategory(sort, page, pagerService, portalService, category);
        int countProducts = cartService.getCountProduct(cartService.getCart(httpSession));

        model.addAttribute("countPage", pagerService.getArrayPage(portalService.getCountPortalNews()));
        model.addAttribute("categories", portalService.getCategories());
        model.addAttribute("portalNewsList", portalNewsList);
        model.addAttribute("header", "Карты");
        model.addAttribute("countPortalNews", portalService.getCountPortalNews(category));
        model.addAttribute("sort", sort);
        model.addAttribute("countProducts", countProducts);
        return "archportal-page";
    }

}
