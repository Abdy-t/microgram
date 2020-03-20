package com.example.microgram.service;

import com.example.microgram.dto.CommentDTO;
import com.example.microgram.model.Comment;
import com.example.microgram.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentDTO addComment(CommentDTO commentDTO) {
        Date time = new Date();
        var comment = Comment.builder()
                .userAccount(commentDTO.getUserAccount())
                .commentText(commentDTO.getCommentText())
                .commentDate(time)
                .build();

        commentRepository.save(comment);
        return CommentDTO.from(comment);
    }

}
