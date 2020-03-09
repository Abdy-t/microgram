package com.example.microgram.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

@Document
public class User {
    public static final User EMPTY = new User("account","email@", "password",
                                             null, null, null,
                                         0,0,0);
    @Id
    private String id;

    private String account;
    private String email;
    private String password;
    private List<Publication> publications;
    private List<Subscription> subscriptions;
    private List<Subscription> followers;
    private int publicationCount;
    private int subscriptionCount;
    private int subscriberCount;

    public User() {}

    public User(String account, String email, String password,
                List<Publication> publications, List<Subscription> subscriptions, List<Subscription> followers,
                int publicationCount, int subscriptionCount,  int subscriberCount) {
        Objects.requireNonNull(account);
        Objects.requireNonNull(email);
        Objects.requireNonNull(password);
        this.id = String.valueOf(account.hashCode()); //UUID.randomUUID().toString();
        this.account = account;
        this.email = email;
        this.password = password;
        this.publications = publications;
        this.subscriptions = subscriptions;
        this.followers = followers;
        this.publicationCount = publicationCount;
        this.subscriptionCount = subscriptionCount;
        this.subscriberCount = subscriberCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPublicationCount() {
        return publicationCount;
    }

    public void setPublicationCount(int publicationCount) {
        this.publicationCount = publicationCount;
    }

    public int getSubscriptionCount() {
        return subscriptionCount;
    }

    public void setSubscriptionCount(int subscriptionCount) {
        this.subscriptionCount = subscriptionCount;
    }

    public int getSubscriberCount() {
        return subscriberCount;
    }

    public void setSubscriberCount(int subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<Subscription> getFollowers() {
        return followers;
    }

    public void setFollowers(List<Subscription> followers) {
        this.followers = followers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", publicationCount=" + publicationCount +
                ", subscriptionCount=" + subscriptionCount +
                ", subscriberCount=" + subscriberCount +
                '}';
    }
}
