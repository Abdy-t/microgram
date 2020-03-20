package com.example.microgram.repository;

import com.example.microgram.model.Subscription;
import com.example.microgram.model.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, String> {

    @Query("{'who' : ?0, 'onWhom':?1}")
    Subscription selectSubscription(String email, String email2);

    Iterable<Subscription> findAllByWho(String email);

    List<Subscription> getAllByWho(String email);

    Iterable<Subscription> findAllByOnWhom(String email);

    @Query("{'who' : ?0}")
    Iterable<Subscription> selectSubscriptionsWithWho(String email);
}
