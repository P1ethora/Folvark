package net.plethora.folvark.service;

import net.plethora.folvark.dao.DaoNewsPortal;
import net.plethora.folvark.dao.DaoPortalCategory;
import net.plethora.folvark.models.PortalCategory;
import net.plethora.folvark.models.PortalNews;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PortalService {

    private final DaoNewsPortal daoNewsPortal;
    private final DaoPortalCategory daoPortalCategory;
    private final int NUMBER_LAST_PRODUCTS = 2;

    public PortalService(DaoNewsPortal daoNewsPortal, DaoPortalCategory daoPortalCategory) {
        this.daoNewsPortal = daoNewsPortal;
        this.daoPortalCategory = daoPortalCategory;
    }

    public List<PortalCategory> getCategories() {
        return daoPortalCategory.findAll();
    }

    public long getCountPortalNews() {
        return daoNewsPortal.getCount();
    }

    public long getCountPortalNews(String category) {
        return daoNewsPortal.getCount(getNameCategory(category));
    }

    public String getNameCategory(String category) {
        return Objects.requireNonNull(getCategories().stream()
                .filter(i -> i.getUrlNameCategory().equals(category))
                .findFirst().orElse(null))
                .getNameCategory();
    }

    public List<PortalNews> fillingPortalNewsList(String sort, String page, PagerService pagerService) {

        List<PortalNews> portalNews;
        if (sort == null) {
            if (page == null) {
                portalNews = pagerService.getAllPortalNewsByPage(0);
            } else if (page.equals("all")) {
                portalNews = pagerService.getAllPortalNews();
            } else {
                portalNews = pagerService.getAllPortalNewsByPage(Integer.parseInt(page) - 1);
            }
        } else {
            if (page == null) {
                portalNews = pagerService.getPagePortalNewsSort(0, sort);
            } else if (page.equals("all")) {
                portalNews = pagerService.getAllPortalNewsSort(sort);
            } else {
                portalNews = pagerService.getPagePortalNewsSort(Integer.parseInt(page) - 1, sort);
            }
        }
        return portalNews;
    }

    public List<PortalNews> fillingPortalNewsListByCategory(String sort, String page, PagerService pagerService, PortalService productService, String category) {

        List<PortalNews> portalNewsList;
        if (sort == null) {
            if (page == null) {
                portalNewsList = pagerService.getPortalNewsByPageByCategory(productService.getNameCategory(category), 0);
            } else if (page.equals("all")) {
                portalNewsList = pagerService.getAllPortalNewsByCategory(productService.getNameCategory(category));
            } else {
                portalNewsList = pagerService.getPortalNewsByPageByCategory(productService.getNameCategory(category), Integer.parseInt(page) - 1);
            }
        } else {
            if (page == null) {
                portalNewsList = pagerService.getPortalNewsByPageByCategorySort(productService.getNameCategory(category), 0, sort);
            } else if (page.equals("all")) {
                portalNewsList = pagerService.getAllPortalNewsByCategorySort(productService.getNameCategory(category), sort);
            } else {
                portalNewsList = pagerService.getPortalNewsByPageByCategorySort(productService.getNameCategory(category), Integer.parseInt(page) - 1, sort);
            }
        }
        return portalNewsList;
    }

    public List<PortalNews> getLastProducts() {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(0, NUMBER_LAST_PRODUCTS, sort);
        return daoNewsPortal.findAll(pageable);
    }

}
