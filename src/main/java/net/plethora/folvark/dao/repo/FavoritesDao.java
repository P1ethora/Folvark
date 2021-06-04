package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.EmailUser;
import net.plethora.folvark.models.system.FavoritesPack;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FavoritesDao extends MongoRepository<FavoritesPack, String> {
}