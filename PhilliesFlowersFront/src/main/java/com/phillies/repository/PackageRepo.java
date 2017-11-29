package com.phillies.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.phillies.domain.FlowerPackage; 


public interface PackageRepo extends MongoRepository<FlowerPackage, Integer>{

}
