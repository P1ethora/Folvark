package net.plethora.folvark.service;

import lombok.Getter;
import lombok.Setter;
import net.plethora.folvark.dao.DaoNewsPortal;
import net.plethora.folvark.dao.DaoProductMap;
import net.plethora.folvark.models.PortalNews;
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

    private final DaoProductMap daoProductMap;
    private final DaoNewsPortal daoNewsPortal;
    static final int SIZE_PAGE = 27;

    public PagerService(DaoProductMap daoProductMap, DaoNewsPortal daoNewsPortal) {
        this.daoProductMap = daoProductMap;
        this.daoNewsPortal = daoNewsPortal;
    }

    //-----------------------------JUST PRODUCT---------------------------------------------------------
    List<ProductMap> getAllProduct() { //весь товар
        return daoProductMap.findAll();
    }

    List<ProductMap> getAllProductByPage(int numberPage) {  //все постранично
        Pageable pageSortCategories = PageRequest.of(numberPage, SIZE_PAGE);
        return daoProductMap.findAll(pageSortCategories);
    }

    //-------------------------------JUST PRODUCT + SORT---------------------------------------------------
    List<ProductMap> getAllProductSort(String property) {//все + сортировка
        Sort sort = Sort.by(Sort.Direction.DESC, property);
        return daoProductMap.findAllSort(sort);
    }

    List<ProductMap> getPageProductSort(int numberPage, String property) {  //весь товар постранично + сортировка
        Sort sort = Sort.by(Sort.Direction.DESC, property);
        Pageable pageSort = PageRequest.of(numberPage, SIZE_PAGE, sort);
        return daoProductMap.findAll(pageSort);
    }

    //-------------------------------CATEGORY------------------------------------------------------------
    List<ProductMap> getAllProductByCategory(String category) {  //весь товар по категориям
        return daoProductMap.findAllByCategory(category);
    }

    List<ProductMap> getProductByPageByCategory(String category, int numberPage) { //товар постранично по категориям
        Pageable page = PageRequest.of(numberPage, SIZE_PAGE);
        return daoProductMap.findByCategoryByPage(category, page);
    }

    //-------------------------------CATEGORY + SORT----------------------------------------------------------------------
    List<ProductMap> getAllProductByCategorySort(String category, String property) { //весь товар по категориям + сортировка
        Sort sort = Sort.by(Sort.Direction.DESC, property);
        return daoProductMap.findByCategorySort(category, sort);
    }

    List<ProductMap> getProductByPageByCategorySort(String category, int numberPage, String property) { //постранично по категориям + сортировка
        Sort sort = Sort.by(Sort.Direction.DESC, property);
        Pageable pageSort = PageRequest.of(numberPage, SIZE_PAGE, sort);
        return daoProductMap.findByCategoryByPage(category, pageSort);
    }

    //----------------------------------PAGE LOGIC--------------------------------------------------------------------------
    public int[] getArrayPage(long countProduct) {
        int countPage = (int) (countProduct / SIZE_PAGE) + 1;
        int[] arrPage = new int[countPage];
        for (int i = 0; i < arrPage.length; i++) {
            arrPage[i] = i + 1;
        }
        return arrPage;
    }

    //-------------------ARCHPORTAL-NEWS----------------------------------------------------------------
//-----------------------------JUST PRODUCT---------------------------------------------------------
    List<PortalNews> getAllPortalNews() { //весь товар
        return daoNewsPortal.findAll();
    }

    List<PortalNews> getAllPortalNewsByPage(int numberPage) {  //все постранично
        Pageable pageSortCategories = PageRequest.of(numberPage, SIZE_PAGE);
        return daoNewsPortal.findAll(pageSortCategories);
    }

    //-------------------------------JUST PRODUCT + SORT---------------------------------------------------
    List<PortalNews> getAllPortalNewsSort(String property) {//все + сортировка
        Sort sort = Sort.by(Sort.Direction.DESC, property);
        return daoNewsPortal.findAllSort(sort);
    }

    List<PortalNews> getPagePortalNewsSort(int numberPage, String property) {  //весь товар постранично + сортировка
        Sort sort = Sort.by(Sort.Direction.DESC, property);
        Pageable pageSort = PageRequest.of(numberPage, SIZE_PAGE, sort);
        return daoNewsPortal.findAll(pageSort);
    }

    //-------------------------------CATEGORY------------------------------------------------------------
    List<PortalNews> getAllPortalNewsByCategory(String category) {  //весь товар по категориям
        return daoNewsPortal.findAllByCategory(category);
    }

    List<PortalNews> getPortalNewsByPageByCategory(String category, int numberPage) { //товар постранично по категориям
        Pageable page = PageRequest.of(numberPage, SIZE_PAGE);
        return daoNewsPortal.findByCategoryByPage(category, page);
    }

    //-------------------------------CATEGORY + SORT----------------------------------------------------------------------
    List<PortalNews> getAllPortalNewsByCategorySort(String category, String property) { //весь товар по категориям + сортировка
        Sort sort = Sort.by(Sort.Direction.DESC, property);
        return daoNewsPortal.findByCategorySort(category, sort);
    }

    List<PortalNews> getPortalNewsByPageByCategorySort(String category, int numberPage, String property) { //постранично по категориям + сортировка
        Sort sort = Sort.by(Sort.Direction.DESC, property);
        Pageable pageSort = PageRequest.of(numberPage, SIZE_PAGE, sort);
        return daoNewsPortal.findByCategoryByPage(category, pageSort);
    }

}