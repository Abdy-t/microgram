package com.example.microgram.dto;

import com.example.microgram.model.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class UserDTO {

    public static UserDTO from(User user) {
        List<String> publicationsId = new ArrayList<>();
        if (user.getPublications() == null){
            publicationsId.add("no publications");
        } else {
            IntStream.range(0, user.getPublications().size()).forEachOrdered(i -> {
                publicationsId.add(user.getPublications().get(i).getId());
            });
        }

        return builder()
                .id(user.getId())
                .account(user.getAccount())
                .email(user.getEmail())
                .password(user.getPassword())
                .publications(publicationsId)
                .publicationCount(user.getPublicationCount())
                .subscriptionCount(user.getSubscriptionCount())
                .subscriberCount(user.getSubscriberCount())
                .build();
    }

    private String id;
    private String account;
    private String email;
    private String password;
    private List<String> publications;
    private int publicationCount;
    private int subscriptionCount;
    private int subscriberCount;
}
