package net.plethora.folvark.service;

import lombok.Getter;
import lombok.Setter;
import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.ProductMap;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Setter
@Getter
@Service
public class PagerService {

    private DaoProductMap daoProductMap;
private String idEndElement;
    private final int SIZE_PAGE = 5;

    public PagerService(DaoProductMap daoProductMap) {
        this.daoProductMap = daoProductMap;
    }

    //-----------------------------JUST PRODUCT---------------------------------------------------------
public List<ProductMap> getAllProduct(){ //весь товар
    return daoProductMap.findAll();
}

    public List<ProductMap> getAllProductByPage(int numberPage){  //все постранично
        Pageable pageSortCategories = PageRequest.of(numberPage, SIZE_PAGE);
        return daoProductMap.findAll(pageSortCategories);
    }

    //-------------------------------JUST PRODUCT + SORT---------------------------------------------------
    public List<ProductMap> getAllProductSort(String property){//все + сортировка
        Sort sort = Sort.by(Sort.Direction.DESC, property);
        return daoProductMap.findAllSort(sort);
    }

    public List<ProductMap> getPageProductSort(int numberPage, String property){  //весь товар постранично + сортировка
        Sort sort = Sort.by(Sort.Direction.DESC, property);
        Pageable pageSort = PageRequest.of(numberPage, SIZE_PAGE,sort);
        return daoProductMap.findAll(pageSort);
    }

    //-------------------------------CATEGORY------------------------------------------------------------
    public List<ProductMap> getAllProductByCategory(String category) {  //весь товар по категориям
        return daoProductMap.findAllByCategory(category);
    }

    public List<ProductMap> getProductByPageByCategory(String category, int numberPage) { //товар постранично по категориям
        Pageable page = PageRequest.of(numberPage, SIZE_PAGE);
        return daoProductMap.findByCategoryByPage(category, page);
    }

    //-------------------------------CATEGORY + SORT----------------------------------------------------------------------
    public List<ProductMap> getAllProductByCategorySort(String category, String property) { //весь товар по категориям + сортировка
        Sort sort = Sort.by(Sort.Direction.DESC, property);
        return daoProductMap.findByCategorySort(category, sort);
    }

    public List<ProductMap> getProductByPageByCategorySort(String category, int numberPage, String property) { //постранично по категориям + сортировка
        Sort sort = Sort.by(Sort.Direction.DESC, property);
        Pageable pageSort = PageRequest.of(numberPage, SIZE_PAGE, sort);
        return daoProductMap.findByCategoryByPage(category, pageSort);
    }

}