package com.example.microgram.service;

import com.example.microgram.model.User;
import com.example.microgram.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Component
public class UserAuthService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(s);
        if (user.isPresent())
            return user.get();
        throw new UsernameNotFoundException("User does not exist");
    }
//    @Override
//    public User loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = repository.findByEmail(username);
//        if(user == null) {
//            throw new UsernameNotFoundException("User does not exist");
//        }
//        List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("user"));
//        return new User(user.getId() ,user.getAccount(), user.getEmail(), user.getPassword(), user.getPublications(),user.getPublicationCount(), user.getSubscriberCount(), user.getSubscriptionCount(),authorities);
//    }

}
