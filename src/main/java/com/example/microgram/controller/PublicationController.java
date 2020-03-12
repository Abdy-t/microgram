package com.example.microgram.controller;

import com.example.microgram.model.Publication;
import com.example.microgram.model.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PublicationController {

    @Autowired
    private PublicationRepository taskRepo;

    @DeleteMapping("/public/{id}")
    public Publication deleteTask(@PathVariable String id) {
        Publication publication = taskRepo.findById(id).orElse(new Publication());
        taskRepo.deleteById(id);

        return publication;
    }

    @GetMapping("/public/{id}")
    public Publication getTask(@PathVariable String id) {
        Publication publication = taskRepo.findById(id).orElse(new Publication());

        return publication;
    }

}