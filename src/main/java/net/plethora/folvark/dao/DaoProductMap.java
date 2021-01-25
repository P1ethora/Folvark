package net.plethora.folvark.dao;

import net.plethora.folvark.models.ProductMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoProductMap {

    @Autowired
    private ProductMapRepository productMapRepository;

    public ProductMap findById(String id){
        return productMapRepository.findById(id).orElse(null);
    }

    public List<ProductMap> findAll(){
        return productMapRepository.findAll();
    }

    public void deleteById (String id){
        productMapRepository.deleteById(id);
    }

    public void save (ProductMap productMap){
        productMapRepository.save(productMap);
    }

    public void edit (ProductMap productMap) {
        save(productMap);
    }
}