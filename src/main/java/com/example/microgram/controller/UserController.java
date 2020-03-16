package com.example.microgram.controller;

import com.example.microgram.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

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

    @PostMapping("/subscribe")
    public Subscription subscribe(@RequestBody Subscription subscription) {
        subscriptionRepository.save(subscription);
        return subscription;
    }
//    @PostMapping("/like")
//    public Publication

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

    @GetMapping("/checkUser/{email}")
    public boolean checkEmail(@PathVariable("email") String email) {
        return userRepository.existsByEmail(email);
    }

    @GetMapping("/findUser/{email}")
    public Optional<User> findUser(@PathVariable("email") String email) {
        return userRepository.findByEmail(email);
    }

    @GetMapping("/watchPublication/{email}")
    public List<Publication> watchPublication(@PathVariable("email") String email) {
        List<Subscription> sb = subscriptionRepository.findByWho(email);
        List<User> usr = new ArrayList<>();
        List<Publication> publications = new ArrayList<>();
        int i = 0;
        for(Subscription s : sb){
            String emailSub = s.getOnWhom();
            User newUser = userRepository.getByEmail(emailSub);
            usr.add(newUser);
            i++;
        }
        int j = 0;
        for (User u : usr) {
            publications.addAll(u.getPublications());
        }
        return publications;
    }
    @GetMapping("/watchPublication")
    public Iterable<Publication> watchAllPublication() {
        return publicationRepository.findAll();
    }

}
