package com.example.microgram.service;

import com.example.microgram.dto.SubscriptionDTO;
import com.example.microgram.dto.UserDTO;
import com.example.microgram.exception.ResourceNotFoundException;
import com.example.microgram.model.Subscription;
import com.example.microgram.repository.SubscriptionRepository;
import com.example.microgram.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository){
        this.subscriptionRepository=subscriptionRepository;
        this.userRepository=userRepository;
    }

    public SubscriptionDTO addSubscribe(String id, String id2, SubscriptionDTO subscriptionDTO){
        Date time = new Date();
        var user1 = userRepository.getById(id);
        var user2 = userRepository.getById(id2);
        var sub = Subscription.builder()
                .id(subscriptionDTO.getId())
                .who(user1.getEmail())
                .onWhom(user2.getEmail())
                .eventDate(time)
                .build();
        if (subscriptionRepository.selectSubscription(user1.getEmail(), user2.getEmail()) == null) {
        subscriptionRepository.save(sub);
        }
        return SubscriptionDTO.from(sub);
    }

    public boolean deleteSubscribe(String id, String id2){
        var user1 = userRepository.getById(id);
        var user2 = userRepository.getById(id2);
        var s = subscriptionRepository.selectSubscription(user1.getEmail(), user2.getEmail());
        if (subscriptionRepository.existsById(s.getId())){
            subscriptionRepository.deleteById(s.getId());
            return true;
        } return false;

    }

    public Iterable<Subscription> showSubscriptions(String id){
        var user = userRepository.getById(id);
        return subscriptionRepository.findAllByWho(user.getEmail());
    }

    public Iterable<Subscription> showFollowers(String id){
        var user = userRepository.getById(id);
        return subscriptionRepository.findAllByOnWhom(user.getEmail());
    }
}
