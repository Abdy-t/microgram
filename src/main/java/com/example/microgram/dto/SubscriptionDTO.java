package com.example.microgram.dto;

import com.example.microgram.model.Like;
import com.example.microgram.model.Subscription;
import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class SubscriptionDTO {

    public static SubscriptionDTO from(Subscription subscription) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(subscription.getEventDate());
        return builder()
                .id(subscription.getId())
                .who(subscription.getWho())
                .onWhom(subscription.getOnWhom())
                .eventDate(strDate)
                .build();
    }

    private String id;
    private String who;
    private String onWhom;
    private String eventDate;
}
