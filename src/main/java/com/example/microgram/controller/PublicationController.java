package com.example.microgram.controller;

import com.example.microgram.model.Publication;
import com.example.microgram.model.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class PublicationController {

    @Autowired
    private PublicationRepository publicationRepository;

    @DeleteMapping("/public/{id}")
    public Publication deleteTask(@PathVariable String id) {
        Publication publication = publicationRepository.findById(id).orElse(new Publication());
        publicationRepository.deleteById(id);

        return publication;
    }

    @GetMapping("/public/{id}")
    public Publication getTask(@PathVariable String id) {
        Publication publication = publicationRepository.findById(id).orElse(new Publication());

        return publication;
    }
    @PutMapping("/like")
    public Publication likePublication (@RequestBody Publication publication) {
        String id = publication.getId();
        return publicationRepository.findById(id)
                .map(p -> {
                    p.setLikes(publication.getLikes());
                    return publicationRepository.save(p);
                }).orElseGet(() -> {
                    publication.setId(id);
                    return publicationRepository.save(publication);
                });
    }
//    @GetMapping("/publicSubscription/{publications}}")
//    public Iterable<Publication> getPublications(@PathVariable("publications") String email) {
//
//    }

}