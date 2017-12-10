package com.phillies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phillies.domain.Flower;
import com.phillies.repository.FlowerRepo;

@Service
public class FlowerServiceImp implements FlowerService {

	@Autowired
	private FlowerRepo flowerRepo;

	@Override
	public Flower getFlower(String name) {
		System.out.println("INPUT:" + name);
		Flower flower = new Flower();
		List<Flower> flowers = flowerRepo.findAll();
		for(Flower f:flowers) {
			if(f.getName().equalsIgnoreCase(name))
				flower = f;
		}
		System.out.println(flower.getName());
		return flower;
	}
	
	@Override
	public void updateFlowerStock(String name) {
		Flower flower = getFlower(name);
		int stock = flower.getStock()-5;
		flower.setStock(stock);
		System.out.println(flower.getName() + " " + flower.getStock());
		flowerRepo.save(new Flower(flower.getId(), flower.getName(), flower.getStock(), flower.getPrice()));
		
	}
	

}