package com.example.microgram.controller;

import com.example.microgram.annotations.ApiPageable;
import com.example.microgram.dto.SubscriptionDTO;
import com.example.microgram.dto.UserDTO;
import com.example.microgram.model.*;
import com.example.microgram.service.SubscriptionService;
import com.example.microgram.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/main")
public class UserController {
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    public UserController(UserService userService, SubscriptionService subscriptionService) {
        this.userService=userService;
        this.subscriptionService=subscriptionService;
    }

    @ApiPageable
    @GetMapping
    public Slice<UserDTO> findUsers(@ApiIgnore Pageable pageable) {
        return userService.findUsers(pageable);
    }

    @ApiPageable
    @GetMapping("/{id}/users")
    public Slice<UserDTO> showUsers(@PathVariable String id,@ApiIgnore Pageable pageable) {
        return userService.showUsers(id, pageable);
    }

    @PostMapping(path="/{id}/users/{id2}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SubscriptionDTO addSubscribe(@PathVariable String id, @PathVariable String id2, @RequestBody SubscriptionDTO subscriptionDTO) {
        return subscriptionService.addSubscribe(id, id2, subscriptionDTO);
    }

    @DeleteMapping("/{id}/users/{id2}")
    public boolean deleteSubscribe(@PathVariable String id, @PathVariable String id2) {
        return subscriptionService.deleteSubscribe(id, id2);
    }

    @GetMapping("/{id}/subscriptions")
    public Iterable<Subscription> showSubscriptions(@PathVariable String id) {
        return subscriptionService.showSubscriptions(id);
    }

    @GetMapping("/{id}/followers")
    public Iterable<Subscription> showFollowers(@PathVariable String id) {
        return subscriptionService.showFollowers(id);
    }

    @GetMapping("/find/{email}")
    public UserDTO findUser(@PathVariable String email) {
        return userService.findUser(email);
    }

    @GetMapping("/login/{email}/{password}")
    public UserDTO loginUser(@PathVariable String email, @PathVariable String password) {
        return userService.loginUser(email, password);
    }

    @DeleteMapping("/{id}/deleteUser")
    public boolean deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        // merge
//        User userN = userRepository.findById(user.getId()).orElse(user);
//        for (Publication p : user.getPublications()) {
//            if (userN.getPublications().stream().filter(x -> x.getId().equals(p.getId())).count() == 0)
//                userN.getPublications().add(p);
//        }
        return userService.register(userDTO);
    }

//    @GetMapping("/checkLike/{email}/{photo}")
//    public boolean checkLike(@PathVariable("email") String email, @PathVariable("photo") String photo) {
////        boolean result = false;
//        if (likeRepository.existsByMarkAccount(email) && likeRepository.existsByMarkPhoto(photo)) {
//                 return true;
//        }else return false;
//
//    }
}
