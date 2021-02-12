package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.PortalCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsCategoryRepository extends MongoRepository<PortalCategory, String> {

    PortalCategory findByNameCategory(String name);

    PortalCategory findByUrlNameCategory(String urlName);

}
