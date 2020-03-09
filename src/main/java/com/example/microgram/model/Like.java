package com.example.microgram.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Like {

    @Id
    private String id;

    private String markAccount;
    private String markPhoto;
    private String markDate;

    public Like(String markAccount, String markPhoto, String markDate){
        Objects.requireNonNull(markAccount);
        Objects.requireNonNull(markPhoto);
        Objects.requireNonNull(markDate);
        this.id = String.valueOf(markAccount.hashCode()); //UUID.randomUUID().toString();
        this.markAccount = markAccount;
        this.markPhoto = markPhoto;
        this.markDate = markDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarkAccount() {
        return markAccount;
    }

    public void setMarkAccount(String markAccount) {
        this.markAccount = markAccount;
    }

    public String getMarkPhoto() {
        return markPhoto;
    }

    public void setMarkPhoto(String markPhoto) {
        this.markPhoto = markPhoto;
    }

    public String getMarkDate() {
        return markDate;
    }

    public void setMarkDate(String markDate) {
        this.markDate = markDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Like like = (Like) o;
        return Objects.equals(id, like.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, markAccount);
    }

    @Override
    public String toString() {
        return "Like{" +
                "id='" + id + '\'' +
                ", markAccount='" + markAccount + '\'' +
                ", markPhoto='" + markPhoto + '\'' +
                ", markDate='" + markDate + '\'' +
                '}';
    }
}
