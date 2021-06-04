package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.BagMap;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BagMapRepository extends MongoRepository<BagMap,String> {

}
