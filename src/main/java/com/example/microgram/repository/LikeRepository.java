package com.example.microgram.repository;

import com.example.microgram.model.Like;
import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, String> {
        boolean existsByMarkAccount(String email);
        boolean existsByMarkPhoto(String photo);
        Like getById(String id);

}
