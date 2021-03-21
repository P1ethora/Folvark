package net.plethora.folvark.controller;

import net.plethora.folvark.models.PortalNews;
import net.plethora.folvark.service.AuthService;
import net.plethora.folvark.service.PagerService;
import net.plethora.folvark.service.PortalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ArchportalController {

    private final AuthService authService;
    private final PortalService portalService;
    private final PagerService pagerService;

    public ArchportalController(PortalService portalService, AuthService authService,
                                PagerService pagerService) {
        this.authService = authService;
        this.portalService = portalService;
        this.pagerService = pagerService;
    }

    @GetMapping("/archportal")
//    @PreAuthorize( "hasAuthority('developers:read')")
    public String pageArchportal(@RequestParam(required = false) String sort, @RequestParam(required = false) String page, HttpSession httpSession, Model model) {
        authService.checkCart(httpSession);
        authService.viewUserAccount(model);

        List<PortalNews> portalNewsList = portalService.fillingPortalNewsList(sort, page, pagerService);

        portalAttribute(model, portalNewsList, null, sort, page, httpSession);

        return "archportal-page";
    }

    @GetMapping("/archportal/{category}")
//    @PreAuthorize( "hasAuthority('developers:read')")
    public String archCategory(@RequestParam(required = false) String sort, @RequestParam(required = false) String page, @PathVariable("category") String category, HttpSession httpSession, Model model) {
        authService.checkCart(httpSession);
        authService.viewUserAccount(model);

        List<PortalNews> portalNewsList = portalService.fillingPortalNewsListByCategory(sort, page, pagerService, portalService, category);

        portalAttribute(model, portalNewsList, category, sort, page, httpSession);

        return "archportal-page";
    }

    private void portalAttribute(Model model, List<PortalNews> portalNews, String category, String sort, String page, HttpSession httpSession) {

        int countProduct = authService.countProduct(httpSession);
        model.addAttribute("countPage", pagerService.getArrayPage(portalService.getCountPortalNews()));
        model.addAttribute("categories", portalService.getCategories());
        model.addAttribute("countProducts", countProduct);

        if (category != null) {

            model.addAttribute("portalNewsList", portalNews);
            model.addAttribute("header", portalService.getNameCategory(category));
            model.addAttribute("countPortalNews", portalService.getCountPortalNews(category));
            model.addAttribute("sort", sort);

        } else {
            model.addAttribute("portalNewsList", portalNews);
            model.addAttribute("header", "Археопортал");
            model.addAttribute("countPortalNews", portalService.getCountPortalNews());
            model.addAttribute("sort", sort);
        }

        if (page != null) {
            model.addAttribute("pageN", "'?page='" + page);
        } else {
            model.addAttribute("pageN", "");
        }
    }
}