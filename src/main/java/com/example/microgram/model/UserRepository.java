package com.example.microgram.model;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    private String getName() {
        return "aaaa";
    }
    //добавить поиск пользователей по имени

    //проверить существование пользователя по электронной почте при регистрации

    //проверить существование пользователя по электронной почте при авторизации и правильности введенного пароля

    //метод добавления публикации

    //метод удаления публикации

    //метод добавляющий комментарий к публикации

    //метод оформлений лайка к публикации
}
