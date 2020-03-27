package com.example.microgram.service;

import com.example.microgram.model.User;
import com.example.microgram.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
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
}
