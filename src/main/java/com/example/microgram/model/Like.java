package com.example.microgram.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "likes")
@Data
public class Like {
    @Id
    private String id;
    private String markAccount;
    private String markPhoto;
    private String markDate;
}
