package com.phillies.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.phillies.domain.Order; 

@Repository
public interface OrderRepo extends MongoRepository<Order, Integer>{
	public List<Order> findAll();
}
