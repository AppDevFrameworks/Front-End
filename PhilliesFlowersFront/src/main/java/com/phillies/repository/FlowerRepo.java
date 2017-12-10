package com.phillies.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.phillies.domain.Flower;

@Repository
public interface FlowerRepo extends MongoRepository<Flower, Integer>{

}
