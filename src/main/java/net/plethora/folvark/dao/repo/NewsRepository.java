package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.PortalNews;
import net.plethora.folvark.models.ProductMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface NewsRepository extends MongoRepository<PortalNews, String> {

    Page<PortalNews> findByCategory(String category, Pageable pageable);// постраничный по категориям

    List<PortalNews> findByCategory(String category);// по категориям все сразу

    List<PortalNews> findByCategory(String category, Sort sort);

    long countByCategory(String category);

}
