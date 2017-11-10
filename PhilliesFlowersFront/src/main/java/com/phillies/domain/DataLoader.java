package com.phillies.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.phillies.repository.FlowerRepo;

@Component
public class DataLoader implements ApplicationRunner {
	
	String[] flowerName = {"Rose","Tulip","Daisy","Carnation","Lily","Orchid","Hypericum","Sunflower"};
	int[] flowerStock = {50,50,50,50,50,50,50,50};
	float[] flowerPrice = {(float) 1.80,(float) 1.50,(float) 1.45,(float) 1.70,(float) 1.90,(float) 1.50,(float) 1.75,(float) 1.30};
	@Autowired
	FlowerRepo flowerRepo;
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		for(int i = 0; i < flowerName.length; i++) {
			flowerRepo.save(new Flower(1+i,flowerName[i], flowerStock[i], flowerPrice[i]));
		}
	}

}
