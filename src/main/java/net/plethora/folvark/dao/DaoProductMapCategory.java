package net.plethora.folvark.dao;

import net.plethora.folvark.dao.repo.ProductMapCategoryRepository;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.ProductMapCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoProductMapCategory {

    private ProductMapCategoryRepository productMapCategoryRepository;

    public DaoProductMapCategory(ProductMapCategoryRepository productMapCategoryRepository) {
        this.productMapCategoryRepository = productMapCategoryRepository;
    }

    public ProductMapCategory findByNameCategory(String name) {
        return productMapCategoryRepository.findByNameCategory(name);
    }

    public ProductMapCategory findByUrlNameCategory(String urlName) {
        return productMapCategoryRepository.findByUrlNameCategory(urlName);
    }

    public List<ProductMapCategory> findAll() {
        return productMapCategoryRepository.findAll();
    }

    public void saveProductMapCategory(ProductMapCategory productMapCategory) {
        productMapCategoryRepository.save(productMapCategory);
    }

}
