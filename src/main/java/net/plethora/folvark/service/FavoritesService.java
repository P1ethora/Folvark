package net.plethora.folvark.service;

import lombok.AllArgsConstructor;
import net.plethora.folvark.dao.repo.FavoritesDao;
import net.plethora.folvark.dao.repo.ProductMapRepository;
import net.plethora.folvark.models.ProductMap;
import net.plethora.folvark.models.system.FavoritesPack;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@AllArgsConstructor
@Service
public class FavoritesService {

    private final FavoritesDao favoritesDao;
    private final ProductMapRepository productMapRepository;

    public Iterable<ProductMap> getFavoritesProduct(String idFavoritesPack) {

        Iterable<String> iterable = favoritesDao.findById(idFavoritesPack).orElse(null).getIdFavorites();
       return productMapRepository.findAllById(iterable);
    }

    public void addProductToFavorites(String idProduct, String idFavoritesPack) {
        FavoritesPack favoritesPack = favoritesDao.findById(idFavoritesPack).orElse(null);
        if(favoritesPack.getIdFavorites()==null){
            favoritesPack.setIdFavorites(new ArrayList<>());
        }
        favoritesPack.getIdFavorites().add(idProduct);
        saveFavPack(favoritesPack);
    }

    public void deleteProductToFavorites(String idProduct, String idFavoritesPack) {
        FavoritesPack favoritesPack = favoritesDao.findById(idFavoritesPack).orElse(null);
        favoritesPack.getIdFavorites().remove(idProduct);
        saveFavPack(favoritesPack);
    }

    public void saveFavPack(FavoritesPack favoritesPack){
        favoritesDao.save(favoritesPack);
    }

    public FavoritesPack getById(String idFavoritesPack) {
        return favoritesDao.findById(idFavoritesPack).orElse(null);
    }



}
