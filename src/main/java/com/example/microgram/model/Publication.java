package com.example.microgram.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "publications")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class Publication {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String photo;
    private String description;
    private Date publicationDate;
    @DBRef
    private List<Comment> comments;
    @DBRef
    private List<Like> likes;

}