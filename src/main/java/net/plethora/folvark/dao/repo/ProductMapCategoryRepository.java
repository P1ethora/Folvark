package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.ProductMapCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMapCategoryRepository extends MongoRepository<ProductMapCategory, String> {

    ProductMapCategory findByNameCategory(String name);

    ProductMapCategory findByUrlNameCategory(String urlName);

}
