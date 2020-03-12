package com.example.microgram.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;
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
