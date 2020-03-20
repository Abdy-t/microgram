package com.example.microgram.repository;

import com.example.microgram.model.Publication;
import org.springframework.data.repository.CrudRepository;

public interface PublicationRepository extends CrudRepository<Publication, String> {
    Publication getById(String id);

    //вывод всех публикаций юзера

    //вывод всех публикаций юзеров на которых подписан юзер

    //сортировка публикаций по дате
}
