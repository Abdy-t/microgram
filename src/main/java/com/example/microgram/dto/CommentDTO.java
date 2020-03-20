package com.example.microgram.dto;

import com.example.microgram.model.Comment;
import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class CommentDTO {

    public static CommentDTO from(Comment comment) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(comment.getCommentDate());
        return builder()
                .id(comment.getId())
                .userAccount(comment.getUserAccount())
                .commentText(comment.getCommentText())
                .commentDate(strDate)
                .build();
    }

    private String id;
    private String userAccount;
    private String commentText;
    private String commentDate;
}
