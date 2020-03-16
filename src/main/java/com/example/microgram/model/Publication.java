package com.example.microgram.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "publications")
@Data
public class Publication {
    @Id
    private String id;
    private String photo;
    private String description;
    private String publicationDate;
    @DBRef
    private List<Comment> comments;
    @DBRef
    private List<Like> likes;

//    @Data
//    public class Comment {
//        private String userAccount;
//        private String commentText;
//        private String commentDate;
//    }
//    @Data
//    public class Like {
//        private String markAccount;
//        private String markPhoto;
//        private String markDate;
//    }
}