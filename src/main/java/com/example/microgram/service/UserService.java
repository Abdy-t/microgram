package com.example.microgram.service;
import com.example.microgram.dto.UserDTO;
import com.example.microgram.exception.ResourceNotFoundException;
import com.example.microgram.model.Publication;
import com.example.microgram.model.User;

import com.example.microgram.repository.PublicationRepository;
import com.example.microgram.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@Service
public class UserService {
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;


    public UserService(UserRepository userRepository, PublicationRepository publicationRepository){
        this.userRepository=userRepository;
        this.publicationRepository=publicationRepository;

    }

    public void addPublication(String userId, String publicationId){
        Publication publication = publicationRepository.getById(publicationId);
        var user = userRepository.getById(userId);
        List<Publication> pblList = new ArrayList<>();
        pblList.add(publication);
        if (user.getPublications() != null) {
            IntStream.range(0, pblList.size()).forEachOrdered(i -> pblList.add(user.getPublications().get(i)));
        }
        user.setPublications(pblList);
        var userUpdate = User.builder()
                .id(user.getId())
                .account(user.getAccount())
                .email(user.getEmail())
                .password(user.getPassword())
                .publications(user.getPublications())
                .publicationCount(user.getPublicationCount())
                .subscriptionCount(user.getSubscriptionCount())
                .subscriberCount(user.getSubscriberCount())
                .build();
        userRepository.save(userUpdate);
        UserDTO.from(userUpdate);
    }

    public UserDTO register(UserDTO userDTO){
        var user = User.builder()
                .account(userDTO.getAccount())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();
        userRepository.save(user);
        return UserDTO.from(user);
    }

    public Slice<UserDTO> findUsers(Pageable pageable) {
        Page<User> slice = userRepository.findAll(pageable);
        return slice.map(UserDTO::from);
    }

    public UserDTO findUser(String email){
        User usr = userRepository.getByEmail(email);
        var user = userRepository.findById(usr.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Can't user movie with the email: " + email));
        return UserDTO.from(user);
    }

    public Slice<UserDTO> showUsers(Pageable pageable) {
        Page<User> usersPage = userRepository.findAll(pageable);
        List<User> userList = usersPage.getContent();
        List<User> users = new ArrayList<>();
        User u = getUser();
        IntStream.range(0, userList.size()).forEachOrdered(i -> {
            if (!userList.get(i).getId().equals(u.getId()))
            users.add(userList.get(i));
        });
        Page<User> page = new PageImpl<>(users, pageable, users.size());
        return page.map(UserDTO::from);
    }

    public UserDTO loginUser(String email, String password) {
        var us = userRepository.selectUser(email, password);
        var user = userRepository.findById(us.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Can't find user with the email: " + email));
        return UserDTO.from(user);
    }

    public boolean deleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else return false;
    }

    public User getUser() {
        // get current authenticated user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(auth.getName()).get();
    }

}
