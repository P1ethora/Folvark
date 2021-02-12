package net.plethora.folvark.dao;

import net.plethora.folvark.dao.repo.NewsCategoryRepository;
import net.plethora.folvark.models.PortalCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoPortalCategory {

    private final NewsCategoryRepository newsCategoryRepository;

    public DaoPortalCategory(NewsCategoryRepository newsCategoryRepository) {
        this.newsCategoryRepository = newsCategoryRepository;
    }

    public PortalCategory findByNameCategory(String name) {
        return newsCategoryRepository.findByNameCategory(name);
    }

    public PortalCategory findByUrlNameCategory(String urlName) {
        return newsCategoryRepository.findByUrlNameCategory(urlName);
    }

    public List<PortalCategory> findAll() {
        return newsCategoryRepository.findAll();
    }

    public void saveProductMapCategory(PortalCategory portalCategory) {
        newsCategoryRepository.save(portalCategory);
    }

}
