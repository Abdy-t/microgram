package com.example.microgram.model;

import org.springframework.data.repository.CrudRepository;

public interface LikeRepository extends CrudRepository<Like, String> {
        boolean existsByMarkAccount(String email);
        boolean existsByMarkPhoto(String photo);

}
