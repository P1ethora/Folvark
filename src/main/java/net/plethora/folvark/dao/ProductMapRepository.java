package net.plethora.folvark.dao;

import net.plethora.folvark.models.ProductMap;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductMapRepository  extends MongoRepository<ProductMap, String> {
}


