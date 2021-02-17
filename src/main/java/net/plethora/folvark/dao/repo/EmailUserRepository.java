package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.EmailUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailUserRepository extends MongoRepository<EmailUser, String> {

}