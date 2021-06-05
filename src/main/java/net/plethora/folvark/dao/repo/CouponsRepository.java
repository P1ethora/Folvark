package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.Coupon;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CouponsRepository extends MongoRepository<Coupon,String> {
}
