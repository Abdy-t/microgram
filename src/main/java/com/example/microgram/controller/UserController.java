package com.example.microgram.controller;

import com.example.microgram.model.Publication;
import com.example.microgram.model.PublicationRepository;
import com.example.microgram.model.User;
import com.example.microgram.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @PostMapping("/user")
    public User createUser(@RequestBody User user) {
        // merge
        User userN = userRepository.findById(user.getId()).orElse(user);
        for (Publication p : user.getPublications()) {
            if (userN.getPublications().stream().filter(x -> x.getId().equals(p.getId())).count() == 0)
                userN.getPublications().add(p);
        }
        // save
        List<Publication> publications = userN.getPublications();
        for (Publication p : publications)
            publicationRepository.save(p);

        userRepository.save(userN);

        return userN;
    }

    @DeleteMapping("/user/{id}")
    public User deleteUser(@PathVariable String id) {
        User user = userRepository.findById(id).orElse(new User());
        userRepository.deleteById(id);
        return user;
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id) {
        User user = userRepository.findById(id).orElse(new User());
        return user;
    }

}
