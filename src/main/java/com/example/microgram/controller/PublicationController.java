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
    @GetMapping
    public Slice<PublicationDTO> getAllPublications(@ApiIgnore Pageable pageable) {
        return publicationService.getAllPublications(pageable);
    }

    @ApiPageable
    @GetMapping("/page/watch")
    public Slice<PublicationDTO> getTapePublications(@ApiIgnore Pageable pageable) {
        var user = userService.getUser();
        String id = user.getId();
        return publicationService.getTapePublications(id, pageable);
    }

    @ApiPageable
    @GetMapping("/page")
    public Slice<PublicationDTO> getPublications(@ApiIgnore Pageable pageable) {
        var user = userService.getUser();
        String id = user.getId();
        return publicationService.getPublications(id, pageable);
    }

    @PostMapping(path = "/page", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PublicationDTO addPublication(@RequestBody PublicationDTO publicationDTO) {
        var user = userService.getUser();
        String id = user.getId();
        var publicationDTO1 = publicationService.addPublication(id, publicationDTO);
        userService.addPublication(id, publicationDTO1.getId());
        return publicationDTO1;
    }

    @PostMapping(path="/page/{idPublication}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PublicationDTO addLike(@PathVariable String idPublication, @RequestBody LikeDTO likeDTO) {
        var likeDTO1 = likeService.addLike(likeDTO);
        return publicationService.updateLike(idPublication, likeDTO1);
    }

    @PostMapping(path="/page/{idPublication}/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public PublicationDTO addComment(@PathVariable String idPublication, @RequestBody CommentDTO commentDTO) {
        var user = userService.getUser();
        String id = user.getId();
        var commentDTO1 = commentService.addComment(commentDTO);
        return publicationService.updateComment(idPublication, commentDTO1);
    }

    @DeleteMapping("/page/{idPublication}/comment/{idComment}")
    public boolean deleteComment(@PathVariable String idPublication, @PathVariable String idComment) {
        var user = userService.getUser();
        String id = user.getId();
        return publicationService.deleteComment(id,idPublication, idComment);
    }

    @DeleteMapping("/page/{idPublication}/delete")
    public boolean deletePublication(@PathVariable String idPublication) {
        var user = userService.getUser();
        String id = user.getId();
        return publicationService.deletePublication(id, idPublication);
    }
}