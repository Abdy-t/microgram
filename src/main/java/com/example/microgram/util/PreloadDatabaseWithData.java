package com.example.microgram.util;

import com.example.microgram.model.*;
import com.example.microgram.repository.LikeRepository;
import com.example.microgram.repository.PublicationRepository;
import com.example.microgram.repository.SubscriptionRepository;
import com.example.microgram.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Configuration
public class PreloadDatabaseWithData {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PublicationRepository publicationRepository, LikeRepository likeRepository, SubscriptionRepository subscriptionRepository) {
        return(args) -> {
            List<User> users = readMovies("users.json");
            publicationRepository.deleteAll();
            subscriptionRepository.deleteAll();
            likeRepository.deleteAll();
            userRepository.deleteAll();
            userRepository.saveAll(users);
        };
    }

    private static List<User> readMovies(String fileName) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            var data = Files.readString(Paths.get(fileName));
            var listType = new TypeReference<List<User>>(){};
            return mapper.readValue(data, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

}
