package com.example.microgram.controller;

import com.example.microgram.annotations.ApiPageable;
import com.example.microgram.dto.CommentDTO;
import com.example.microgram.dto.LikeDTO;
import com.example.microgram.dto.PublicationDTO;
import com.example.microgram.service.CommentService;
import com.example.microgram.service.LikeService;
import com.example.microgram.service.PublicationService;
import com.example.microgram.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/publication")
public class PublicationController {

    private final PublicationService publicationService;
    private final UserService userService;
    private final LikeService likeService;
    private final CommentService commentService;


    public PublicationController(PublicationService publicationService, UserService userService,
                                 LikeService likeService, CommentService commentService) {
        this.publicationService=publicationService;
        this.userService=userService;
        this.likeService=likeService;
        this.commentService=commentService;
    }

    @ApiPageable
    @GetMapping("/show/{id}")
    public Slice<PublicationDTO> getTapePublications(@PathVariable String id, @ApiIgnore Pageable pageable) {
        return publicationService.getTapePublications(id, pageable);
    }

    @PostMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PublicationDTO addPublication(@PathVariable String id, @RequestBody PublicationDTO publicationDTO) {
        var publicationDTO1 = publicationService.addPublication(id, publicationDTO);
        userService.addPublication(id, publicationDTO1.getId());
        return publicationDTO1;
    }

    @PostMapping(path="/{id}/{idPublication}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PublicationDTO addLike(@PathVariable String id, @PathVariable String idPublication, @RequestBody LikeDTO likeDTO) {
        var likeDTO1 = likeService.addLike(likeDTO);
        return publicationService.updateLike(idPublication, likeDTO1);
    }

    @PostMapping(path="/{id}/{idPublication}/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PublicationDTO addComment(@PathVariable String id, @PathVariable String idPublication, @RequestBody CommentDTO commentDTO) {
        var commentDTO1 = commentService.addComment(commentDTO);
        return publicationService.updateComment(idPublication, commentDTO1);
    }

    @DeleteMapping("/{id}/{idPublication}/comment/{idComment}")
    public boolean deleteComment(@PathVariable String id, @PathVariable String idPublication, @PathVariable String idComment) {
        return publicationService.deleteComment(idPublication, idComment);
    }

    @DeleteMapping("/{id}/{idPublication}/delete")
    public boolean deletePublication(@PathVariable String id, @PathVariable String idPublication) {
        return publicationService.deletePublication(id, idPublication);
    }
}