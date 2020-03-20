package com.example.microgram.dto;

import com.example.microgram.model.Like;
import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class LikeDTO {

    public static LikeDTO from(Like like) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(like.getMarkDate());
        return builder()
                .id(like.getId())
                .markAccount(like.getMarkAccount())
                .markPhoto(like.getMarkPhoto())
                .markDate(strDate)
                .build();
    }

    private String id;
    private String markAccount;
    private String markPhoto;
    private String markDate;
}
