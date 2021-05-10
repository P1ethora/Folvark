package net.plethora.folvark.dao.repo;

import net.plethora.folvark.models.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByAttachedTo(String idElement);

    List<Comment> findByAttachedTo(String idElement, Sort sort);

    List<Comment> findByIdUserAndDateAndAttachedTo(Long idUser, Date date, String attachedTo);


}
