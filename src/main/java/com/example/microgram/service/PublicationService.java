package com.example.microgram.service;

import com.example.microgram.dto.CommentDTO;
import com.example.microgram.dto.LikeDTO;
import com.example.microgram.dto.PublicationDTO;
import com.example.microgram.model.*;
import com.example.microgram.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final SubscriptionRepository subscriptionRepository;


    public PublicationService(PublicationRepository publicationRepository, UserRepository userRepository, LikeRepository likeRepository, CommentRepository commentRepository, SubscriptionRepository subscriptionRepository) {
        this.publicationRepository=publicationRepository;
        this.userRepository=userRepository;
        this.likeRepository=likeRepository;
        this.commentRepository=commentRepository;
        this.subscriptionRepository=subscriptionRepository;
    }

    public Slice<PublicationDTO> getPublications(String id, Pageable pageable) {
        var user = userRepository.getById(id);
        List<Publication> publications = new ArrayList<>();
        IntStream.range(0, user.getPublications().size()).forEachOrdered(i -> publications.add(user.getPublications().get(i)));
        Page<Publication> page = new PageImpl<>(publications, pageable, publications.size());
        return page.map(PublicationDTO::from);
    }

    public Slice<PublicationDTO> getTapePublications(String id, Pageable pageable) {
        var user = userRepository.getById(id);
        List<Subscription> s = subscriptionRepository.getAllByWho(user.getEmail());
        List<Publication> publicationList = new ArrayList<>();
        List<User> users = new ArrayList<>();
        IntStream.range(0, s.size()).forEachOrdered(i -> {
            users.add(userRepository.getByEmail(s.get(i).getOnWhom()));
        });
        for (User u : users) publicationList.addAll(u.getPublications());

        Page<Publication> page = new PageImpl<>(publicationList, pageable, publicationList.size());
        return page.map(PublicationDTO::from);
    }

    public PublicationDTO addPublication(String id, PublicationDTO publicationDTO) {
        Date time = new Date();
        var publication = Publication.builder()
                .photo(publicationDTO.getPhoto())
                .description(publicationDTO.getDescription())
                .publicationDate(time)
                .build();
        publicationRepository.save(publication);
        return PublicationDTO.from(publication);
    }
    public PublicationDTO updateLike(String id, LikeDTO likeDTO) {
        Like like = likeRepository.getById(likeDTO.getId());
        Publication publication = publicationRepository.getById(id);
        List<Like> likes = new ArrayList<>();
        likes.add(like);

        if (publication.getLikes() != null) {
            IntStream.range(0, publication.getLikes().size()).forEachOrdered(i -> {
                likes.add(publication.getLikes().get(i));
            });
        }
        publication.setLikes(likes);

        var publicationUpdate = Publication.builder()
                .id(publication.getId())
                .photo(publication.getPhoto())
                .description(publication.getDescription())
                .publicationDate(publication.getPublicationDate())
                .comments(publication.getComments())
                .likes(publication.getLikes())
                .build();
        publicationRepository.save(publicationUpdate);
        return PublicationDTO.from(publicationUpdate);
    }
    public PublicationDTO updateComment(String id, CommentDTO commentDTO) {
        Comment comment = commentRepository.getById(commentDTO.getId());
        Publication publication = publicationRepository.getById(id);
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);

        if (publication.getComments() != null) {
            IntStream.range(0, publication.getComments().size()).forEachOrdered(i -> {
                comments.add(publication.getComments().get(i));
            });
        }
        publication.setComments(comments);

        var publicationUpdate = Publication.builder()
                .id(publication.getId())
                .photo(publication.getPhoto())
                .description(publication.getDescription())
                .publicationDate(publication.getPublicationDate())
                .comments(publication.getComments())
                .likes(publication.getLikes())
                .build();
        publicationRepository.save(publicationUpdate);
        return PublicationDTO.from(publicationUpdate);
    }

    public boolean deletePublication(String idU, String idP) {
        if (userRepository.existsById(idU)) {
            User user = userRepository.getById(idU);
            if (user.getPublications() != null) {
                for (Publication p : user.getPublications()) {
                    if (p.getId().equals(idP)) {
                        if (p.getLikes() != null){
                            for (Like l : p.getLikes()) {
                                likeRepository.deleteById(l.getId());
                            }
                        }
                        if (p.getComments() != null){
                            for (Comment c : p.getComments()) {
                                commentRepository.deleteById(c.getId());
                            }
                        }
                    }
                }
                return  true;
            }
        }
        return false;
    }
    public boolean deleteComment(String idP, String idC) {
        if(publicationRepository.existsById(idP)){
            Publication publication = publicationRepository.getById(idP);
            if(publication.getComments() != null){
                for (Comment c : publication.getComments()){
                    commentRepository.deleteById(idC);
                }
            } return true;
        }
        return false;
    }
    public boolean deleteLike(String idP, String idL) {
        if(publicationRepository.existsById(idP)){
            Publication publication = publicationRepository.getById(idP);
            if(publication.getLikes() != null){
                for (Like l : publication.getLikes()){
                    likeRepository.deleteById(idL);
                }
            } return true;
        }
        return false;
    }

}
