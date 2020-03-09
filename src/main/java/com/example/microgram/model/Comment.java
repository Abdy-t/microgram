package com.example.microgram.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Comment {
    @Id
    private String id;

    private String commentText;
    private String commentDate;

    public Comment(String commentText, String commentDate){
        Objects.requireNonNull(commentText);
        Objects.requireNonNull(commentDate);
        this.id = String.valueOf(commentText.hashCode()); //UUID.randomUUID().toString();
        this.commentText = commentText;
        this.commentDate = commentDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commentText);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", commentText='" + commentText + '\'' +
                ", commentDate='" + commentDate + '\'' +
                '}';
    }
}
