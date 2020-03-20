package com.example.microgram.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
@Document(collection = "users")
@Data
public class User {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String account;
    @Indexed
    private String email;
    private String password;
    @DBRef
    private List<Publication> publications;
    private int publicationCount;
    private int subscriptionCount;
    private int subscriberCount;
}
