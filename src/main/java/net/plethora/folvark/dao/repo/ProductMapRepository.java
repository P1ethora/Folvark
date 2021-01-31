package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.ProductMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductMapRepository  extends MongoRepository<ProductMap, String> {

    Page<ProductMap> findByCategory(String category, Pageable pageable);// постраничный по категориям
    List<ProductMap> findByCategory(String category);// по категориям все сразу
    List<ProductMap> findByCategory(String category, Sort sort);

    long countByCategory(String category);
}


