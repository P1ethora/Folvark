package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, String> {


}
