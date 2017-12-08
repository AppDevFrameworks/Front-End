package com.phillies.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.phillies.domain.Account;
import com.phillies.domain.Flower;
import com.phillies.domain.FlowerPackage;
import com.phillies.domain.Order;
import com.phillies.repository.AccountRepo;
import com.phillies.repository.FlowerRepo;
import com.phillies.repository.OrderRepo;
import com.phillies.repository.PackageRepo;

@Component
public class DataLoader implements ApplicationRunner {
	
	String[] flowerName = {"Rose","Tulip","Daisy","Carnation","Lily","Orchid","Hypericum","Sunflower"};
	int[] flowerStock = {50,50,50,50,50,50,50,50};
	float[] flowerPrice = {(float) 1.80,(float) 1.50,(float) 1.45,(float) 1.70,(float) 1.90,(float) 1.50,(float) 1.75,(float) 1.30};
	
	String[] packageName = {"Red Package", "White Package", "Yellow Package", "Colourful Package"};
	String[][] packageFlowers = {{"Rose","Tulip","Hypericum"},{"Rose","Tulip","Hypericum"},{"Rose","Tulip","Hypericum"},{"Rose","Tulip","Hypericum"}};
	String[][] packageItems = {{"Bear","Chocolates"},{"Bear","Chocolates"},{"Bear","Chocolates"},{"Bear","Chocolates"}};
	int[] packageStock = {10,16,8,31};
	float[] packagePrice = {(float) 11.80,(float) 12.50,(float) 13.45,(float) 15.70};
	
	String[] firstname = {"Phillie","Jasmine","Simon","Rob","Jer"};
	String[] lastname = {"Hunter", "Rice", "Cooney", "Crowley", "Cotter"};
	String[] username = {"phillieH","JasmineR","SimonC","RobC","JerC"};
	String[] password = {"Password","Password","Password","Password","Password"};
	
	
	@Autowired
	FlowerRepo flowerRepo;
	
	@Autowired
	PackageRepo packageRepo;
	
	@Autowired
	AccountRepo accountRepo;
	
	@Autowired
	OrderRepo orderRepo;
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception { 
		for(int i = 0; i < flowerName.length; i++) {
			flowerRepo.save(new Flower(1+i,flowerName[i], flowerStock[i], flowerPrice[i]));
		}
		
		System.out.println("COUNT:" + flowerRepo.count());
		
		for(int i = 0; i < packageName.length; i++) {
			packageRepo.save(new FlowerPackage(1+i, packageName[i], packageFlowers[i], packageItems[i],packageStock[i],packagePrice[i]));
		}
		
		for(int i = 0; i < username.length; i++) {
			accountRepo.save(new Account(username[i],password[i],firstname[i],lastname[i]));
		}
	}
	
	public void placeOrder() throws Exception {
		//int id = order.getOrderId();
//		orderRepo.save(new Order(id, order.getFirstName(), 
//				order.getLastName(), order.getEmailAddress(), order.getMobileNo(),
//				order.getOrder()));
	}
}
