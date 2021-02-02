package net.plethora.folvark.service;

import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.dao.DaoProductMapCategory;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.ProductMapCategory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static net.plethora.folvark.service.PagerService.SIZE_PAGE;

@Service
public class ProductService {

    private DaoProductMap daoProductMap;
    private DaoProductMapCategory daoProductMapCategory;
    private final int NUMBER_LAST_PRODUCTS = 15;

    public ProductService(DaoProductMap daoProductMap, DaoProductMapCategory daoProductMapCategory) {
        this.daoProductMap = daoProductMap;
        this.daoProductMapCategory = daoProductMapCategory;
    }

    public List<ProductMapCategory> getCategories() {
        return daoProductMapCategory.findAll();
    }

    public long getCountProduct() {
        return daoProductMap.getCount();
    }

    public long getCountProduct(String category) {
        return daoProductMap.getCount(getNameCategory(category));
    }

    public String getNameCategory(String category) {
        return Objects.requireNonNull(getCategories().stream()
                .filter(i -> i.getUrlNameCategory().equals(category))
                .findFirst().orElse(null))
                .getNameCategory();
    }

    public List<ProductMap> fillingProductList(String sort, String page, PagerService pagerService) {

        List<ProductMap> products;
        if (sort == null) {
            if (page == null) {
                products = pagerService.getAllProductByPage(0);
            } else if (page.equals("all")) {
                products = pagerService.getAllProduct();
            } else {
                products = pagerService.getAllProductByPage(Integer.parseInt(page) - 1);
            }
        } else {
            if (page == null) {
                products = pagerService.getPageProductSort(0, sort);
            } else if (page.equals("all")) {
                products = pagerService.getAllProductSort(sort);
            } else {
                products = pagerService.getPageProductSort(Integer.parseInt(page) - 1, sort);
            }
        }
        return products;
    }

    public List<ProductMap> fillingProductListByCategory(String sort, String page, PagerService pagerService, ProductService productService, String category) {

        List<ProductMap> products;
        if (sort == null) {
            if (page == null) {
                products = pagerService.getProductByPageByCategory(productService.getNameCategory(category), 0);
            } else if (page.equals("all")) {
                products = pagerService.getAllProductByCategory(productService.getNameCategory(category));
            } else {
                products = pagerService.getProductByPageByCategory(productService.getNameCategory(category), Integer.parseInt(page) - 1);
            }
        } else {
            if (page == null) {
                products = pagerService.getProductByPageByCategorySort(productService.getNameCategory(category), 0, sort);
            } else if (page.equals("all")) {
                products = pagerService.getAllProductByCategorySort(productService.getNameCategory(category), sort);
            } else {
                products = pagerService.getProductByPageByCategorySort(productService.getNameCategory(category), Integer.parseInt(page) - 1, sort);
            }
        }
        return products;
    }

    public List<ProductMap> getLastProducts() {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(0, 15, sort);
        return daoProductMap.findAll(pageable);
    }
}