package com.example.microgram.util;

import com.example.microgram.model.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Configuration
public class PreloadDatabaseWithData {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository) {

        userRepository.deleteAll();
        return (args) -> Stream.of(users())
                .peek(System.out::println)
                .forEach(userRepository::save);
    }

    private User getRandomUser() {
        String[] x = {"1.jpg", "2.jpg", "3.jpg"};
        User newUser = new User();
        Random random = new Random();
        int randInt = random.nextInt(3);
//        String photo = x[randInt]; // publish
        String account = Generator.makeName();
        String email = Generator.makeEmail();
        String password = Generator.makePassword();
//        String description = Generator.makeDescription(); // description
//        List<Publication> publications = new ArrayList<>();
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        LocalDateTime now = LocalDateTime.now();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        String time = format.format(now);
//        publications.add(new Publication(photo, photo, time, null, null));
//        List<Subscription>
//        newUser.getPublications().size()
        List<Publication> pList = getRandomPublication();
        newUser = new User(account, email, password,  pList, null,null, pList.size(),0,0);

        return newUser;
    }

    private List<Publication> getRandomPublication() {
        List<Publication> publications = new ArrayList<>();
        String[] x = {"1.jpg", "2.jpg", "3.jpg"};
        Random random = new Random();
        int randPubl = random.nextInt(4) + 1;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);
        List<Comment> comments = new ArrayList<>();
        List<Like> likes = new ArrayList<>();
        for (int j = 0; j < randPubl; j++) {
            int randInt = random.nextInt(3);
            String photo = x[randInt];
            String description = Generator.makeDescription();
            publications.add(new Publication(photo, description, time, null, null));
        }

        for (int i = 0; i < publications.size(); i++) {
            String comment = Generator.makeDescription();
            String name = Generator.makeName();
            comments.add(new Comment(comment, time));
            likes.add(new Like(name, publications.get(i).getPhoto(), time));
        }

        for (int i = 0; i < publications.size(); i++) {
            publications.get(i).setComments(comments);
            publications.get(i).setLikes(likes);
        }
        return publications;
    }

    private User[] users() {
        return new User[]{
                getRandomUser(),
                getRandomUser(),
                getRandomUser(),
                getRandomUser(),
                getRandomUser()};
    }
}
