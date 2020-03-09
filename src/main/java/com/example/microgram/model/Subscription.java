package com.example.microgram.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Subscription {

    @Id
    private String id;

    private String who;
    private String onWhom;
    private String eventDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        id = id;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getOnWhom() {
        return onWhom;
    }

    public void setOnWhom(String onWhom) {
        this.onWhom = onWhom;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription subscriptions = (Subscription) o;
        return Objects.equals(id, subscriptions.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, who);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id='" + id + '\'' +
                ", who='" + who + '\'' +
                ", onWhom='" + onWhom + '\'' +
                ", eventDate='" + eventDate + '\'' +
                '}';
    }
}
