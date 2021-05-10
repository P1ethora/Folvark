package net.plethora.folvark.service;

import net.plethora.folvark.dao.repo.CommentRepository;
import net.plethora.folvark.models.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

//    public List<Comment> getCommentsFromElement(String idElement) {
//
//        return commentRepository.findByAttachedTo(idElement);
//    }

    public Comment getComment(String id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<Comment> getCommentsFromElementDESC(String idElement) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        return commentRepository.findByAttachedTo(idElement, sort);
    }

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public Comment getComment(long idUser, Date date, String attachedTo) {
       return commentRepository.findByIdUserAndDateAndAttachedTo( idUser,date,attachedTo).get(0);
    }

}