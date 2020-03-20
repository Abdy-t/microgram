package com.example.microgram.repository;

import com.example.microgram.model.Comment;
import com.example.microgram.model.Like;
import org.springframework.data.repository.CrudRepository;


public interface CommentRepository extends CrudRepository<Comment, String> {
    Comment getById(String id);
}
