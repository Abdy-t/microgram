package com.example.microgram.model;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, String> {

    //метод оформления подписки на какого-то юзера
    List<Subscription> findByWho(String email);
}
