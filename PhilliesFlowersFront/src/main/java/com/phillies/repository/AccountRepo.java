package com.phillies.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.phillies.domain.Account; 

@Repository
public interface AccountRepo extends MongoRepository<Account, String>{
	public Account findByUsernameIgnoreCaseAndPassword(String username, String password);
}
