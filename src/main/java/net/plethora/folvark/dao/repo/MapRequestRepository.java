package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.MapRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MapRequestRepository extends MongoRepository<MapRequest, String> {
}
