package com.example.microgram.service;

import com.example.microgram.dto.LikeDTO;
import com.example.microgram.model.Like;
import com.example.microgram.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LikeService {
    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public LikeDTO addLike(LikeDTO likeDTO) {
        Date time = new Date();
        var like = Like.builder()
                .markAccount(likeDTO.getMarkAccount())
                .markPhoto(likeDTO.getMarkPhoto())
                .markDate(time)
                .build();
        likeRepository.save(like);
        return LikeDTO.from(like);
    }
}
