package com.example.microgram.service;

import com.example.microgram.model.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService {
    private final SubscriptionRepository repository;

    public SubscriptionService(SubscriptionRepository repository){
        this.repository=repository;
    }

    public void addSubscribe(String userId, String account){
        //реализовать подписку
    }

    public void deleteSubscribe(String userId, String account){
        //реализовать отписку
    }
    public void showFollowers(String userId){
        //реализовать показ подписчиков юзера
    }
    public void showSubscribes(String userId){
        //реализовать показ подписок
    }
}
