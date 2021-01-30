package net.plethora.folvark.dao;

import net.plethora.folvark.dao.repo.ProductMapRepository;
import net.plethora.folvark.models.ProductMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoProductMap {

    @Autowired
    private ProductMapRepository productMapRepository;

    public List<ProductMap> findAll() {
        return productMapRepository.findAll();
    } //показать все

    public List<ProductMap> findAll(Pageable pageable) {
        return productMapRepository.findAll(pageable).getContent();
    }

        public List<ProductMap> findAllSort(Sort sort) {
            return productMapRepository.findAll(sort);

    } //показать все отсортированное

    public List<ProductMap> findAllByCategory(String category){
        return productMapRepository.findByCategory(category);
    }

    public List<ProductMap> findByCategory(String category, Pageable pageable){
        return productMapRepository.findByCategory(category, pageable).getContent();
    }


public List<ProductMap> findByCategorySortPrice(String category){
        return productMapRepository.findByCategory(category);
}

    public List<ProductMap> findByCategorySortPrice(String category, Sort sort){

        return productMapRepository.findByCategory(category,sort);
    }

    public ProductMap findById(String id) {
        return productMapRepository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        productMapRepository.deleteById(id);
    }

    public void save(ProductMap productMap) {
        productMapRepository.save(productMap);
    }

    public void edit(ProductMap productMap) {
        save(productMap);
    }

    public long getCount() {
        return productMapRepository.count();
    }
}