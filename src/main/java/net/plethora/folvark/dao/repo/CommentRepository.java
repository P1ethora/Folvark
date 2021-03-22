package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    public List<Comment> findByAttachedTo(String idElement);

}
