package com.example.microgram.service;

import com.example.microgram.model.PublicationRepository;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {
    private final PublicationRepository repository;

    public PublicationService(PublicationRepository repository){
        this.repository=repository;
    }

    public void addComment(String idUser, String idPublication, String text) {
        //реализовать добавление коментария к публикации
    }
    public void deleteComment(String idUser, String idPublication, String text) {
        //реализовать удаление коментария к публикации
    }
    public void addLike(String idUser, String idPublication) {
        //реализовать добавление "мне понравилось" к публикации
    }
}
