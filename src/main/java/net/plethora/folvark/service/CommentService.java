package net.plethora.folvark.service;

import net.plethora.folvark.dao.repo.CommentRepository;
import net.plethora.folvark.models.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsFromElement(String idElement) {

        return commentRepository.findByAttachedTo(idElement);
    }
}