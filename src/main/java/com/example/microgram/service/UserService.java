package com.example.microgram.service;
import com.example.microgram.model.User;
import com.example.microgram.model.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository){
        this.repository=repository;
    }

    public Optional<User> login(String email, String password) {
        //реализовать логику входа в систему
        return Optional.empty();
    }

    public void logOut() {
        //реализовать логику выхода из системы
    }

    public User findUser(String account){
        User user = new User();
        //реализовать поиск по юзеру
        return user;
    }

    public void addPublication(String userId, String photo, String description){
        //реализовать публикацию пользователя
    }

}
