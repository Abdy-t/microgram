package com.example.microgram.model;

import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository<Subscription, String> {

    //метод оформления подписки на какого-то юзера

}
