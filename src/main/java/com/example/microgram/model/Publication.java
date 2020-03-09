package com.example.microgram.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document
public class Publication {
    @Id
    private String id;

    private String photo;
    private String description;
    private String publicationDate;
    private List<Comment> comments;
    private List<Like> likes;


    public Publication(String photo, String description, String publicationDate, List<Comment> comments, List<Like> likes) {
        Objects.requireNonNull(photo);
        Objects.requireNonNull(publicationDate);
        this.id = String.valueOf(photo.hashCode()); //UUID.randomUUID().toString();
        this.photo = photo;
        this.description = description;
        this.publicationDate = publicationDate;
        this.comments = comments;
        this.likes = likes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication publication = (Publication) o;
        return Objects.equals(id, publication.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, photo);
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id='" + id + '\'' +
                ", photo='" + photo + '\'' +
                ", description='" + description + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                '}';
    }
}
