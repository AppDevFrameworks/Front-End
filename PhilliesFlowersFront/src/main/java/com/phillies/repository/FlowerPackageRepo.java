package com.phillies.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.phillies.domain.FlowerPackage; 

@Repository
public interface FlowerPackageRepo extends MongoRepository<FlowerPackage, String>{
	public FlowerPackage findByName(String name);
	public List<FlowerPackage> findAll();
}
