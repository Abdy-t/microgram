package com.example.microgram.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "subscription")
@Data
public class Subscription {
    @Id
    private String id;
    private String who;
    private String onWhom;
    private String eventDate;
}
