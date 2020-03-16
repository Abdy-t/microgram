package com.example.microgram.model;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    User getByEmail(String email);

    //добавить поиск пользователей по имени

    //проверить существование пользователя по электронной почте при регистрации

    //проверить существование пользователя по электронной почте при авторизации и правильности введенного пароля

    //метод добавления публикации

    //метод удаления публикации

    //метод добавляющий комментарий к публикации

    //метод оформлений лайка к публикации
}
