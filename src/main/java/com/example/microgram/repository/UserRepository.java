package com.example.microgram.repository;

import com.example.microgram.model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    User findUserByEmailAndPassword(String email, String password);

    @Query("{'email' : ?0, 'password':?1}")
    User selectUser(String email, String password);

    User getById(String id);

    User getByEmail(String email);

}
