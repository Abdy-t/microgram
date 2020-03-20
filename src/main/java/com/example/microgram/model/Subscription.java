package com.example.microgram.model;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;


@Document(collection = "subscription")
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class Subscription {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String who;
    private String onWhom;
    private Date eventDate;
}
