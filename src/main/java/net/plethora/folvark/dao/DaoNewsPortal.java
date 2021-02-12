package net.plethora.folvark.dao;

import net.plethora.folvark.dao.repo.NewsRepository;
import net.plethora.folvark.models.PortalNews;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoNewsPortal {

    private final NewsRepository newsRepository;

    public DaoNewsPortal(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public List<PortalNews> findAll() {
        return newsRepository.findAll();
    } //показать все

    public List<PortalNews> findAll(Pageable pageable) {
        return newsRepository.findAll(pageable).getContent();
    }

    public List<PortalNews> findAllSort(Sort sort) {
        return newsRepository.findAll(sort);

    } //показать все отсортированное

    public List<PortalNews> findAllByCategory(String category) {
        return newsRepository.findByCategory(category);
    }

    public List<PortalNews> findByCategoryByPage(String category, Pageable pageable) {
        return newsRepository.findByCategory(category, pageable).getContent();
    }


    public List<PortalNews> findByCategorySort(String category, Sort sort) {

        return newsRepository.findByCategory(category, sort);
    }

    public PortalNews findById(String id) {
        return newsRepository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        newsRepository.deleteById(id);
    }

    public void save(PortalNews productMap) {
        newsRepository.save(productMap);
    }

    public void edit(PortalNews productMap) {
        save(productMap);
    }

    public long getCount() {
        return newsRepository.count();
    }

    public long getCount(String category) {
        return newsRepository.countByCategory(category);
    }

}