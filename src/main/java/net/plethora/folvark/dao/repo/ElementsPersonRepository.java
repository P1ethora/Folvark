package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.enumerationmodels.ElementProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ElementsPersonRepository extends MongoRepository<ElementProfile, String> {

}
