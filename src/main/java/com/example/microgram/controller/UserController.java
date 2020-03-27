package com.example.microgram.controller;

import com.example.microgram.annotations.ApiPageable;
import com.example.microgram.dto.SubscriptionDTO;
import com.example.microgram.dto.UserDTO;
import com.example.microgram.model.*;
import com.example.microgram.service.SubscriptionService;
import com.example.microgram.service.UserAuthService;
import com.example.microgram.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final UserAuthService userAuthService;
    private final SubscriptionService subscriptionService;

    public UserController(UserService userService, SubscriptionService subscriptionService, UserAuthService userAuthService) {
        this.userService=userService;
        this.subscriptionService=subscriptionService;
        this.userAuthService=userAuthService;
    }

    @ApiPageable
    @GetMapping
    public Slice<UserDTO> findUsers(@ApiIgnore Pageable pageable) {
        return userService.findUsers(pageable);
    }

    @ApiPageable
    @GetMapping("/users")
    public Slice<UserDTO> showUsers(@ApiIgnore Pageable pageable) {
        return userService.showUsers(pageable);
    }

    @PostMapping(path="/profile/users/{id2}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public SubscriptionDTO addSubscribe( @PathVariable String id2, @RequestBody SubscriptionDTO subscriptionDTO) {
        var user = userService.getUser();
        String id = user.getId();
        return subscriptionService.addSubscribe(id, id2, subscriptionDTO);
    }

    @DeleteMapping("/profile/users/{id2}")
    public boolean deleteSubscribe( @PathVariable String id2) {
        var user = userService.getUser();
        String id = user.getId();
        return subscriptionService.deleteSubscribe(id, id2);
    }

    @GetMapping("/profile/subscriptions")
    public Iterable<Subscription> showSubscriptions() {
        var user = userService.getUser();
        String id = user.getId();
        return subscriptionService.showSubscriptions(id);
    }

    @GetMapping("/profile/followers")
    public Iterable<Subscription> showFollowers() {
        var user = userService.getUser();
        String id = user.getId();
        return subscriptionService.showFollowers(id);
    }

    @GetMapping("/profile/find/{email}")
    public UserDTO findUser(@PathVariable String email) {
        return userService.findUser(email);
    }
    @GetMapping("/{email}/{password}")
    public UserDetails loginUser(@PathVariable String email, @PathVariable String password) {
        return userAuthService.loadUserByUsername(email);
    }

    @DeleteMapping("/profile/deleteUser")
    public boolean deleteUser() {
        var user = userService.getUser();
        String id = user.getId();
        return userService.deleteUser(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);
    }

}
