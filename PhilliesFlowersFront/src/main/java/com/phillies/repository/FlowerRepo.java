package com.phillies.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.phillies.domain.Flower;

public interface FlowerRepo extends MongoRepository<Flower, Integer>{

}
