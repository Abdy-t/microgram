package com.example.microgram.dto;

import com.example.microgram.model.Publication;
import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class PublicationDTO {

    public static PublicationDTO from(Publication publication) {
        List<String> commentsId = new ArrayList<>();
        if (publication.getComments() == null){
            commentsId.add("no comments");
        } else {
            IntStream.range(0, publication.getComments().size()).forEachOrdered(i -> {
                commentsId.add(publication.getComments().get(i).getId());
            });
        }
        List<String> likesId = new ArrayList<>();
        if (publication.getLikes() == null){
            likesId.add("no likes");
        } else {
            IntStream.range(0, publication.getLikes().size()).forEachOrdered(i -> {
                likesId.add(publication.getLikes().get(i).getId());
            });
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(publication.getPublicationDate());
        return builder()
                .id(publication.getId())
                .photo(publication.getPhoto())
                .description(publication.getDescription())
                .publicationDate(strDate)
                .comments(commentsId)
                .likes(likesId)
                .build();
    }

    private String id;
    private String photo;
    private String description;
    private String publicationDate;
    private List<String> comments;
    private List<String> likes;

}
