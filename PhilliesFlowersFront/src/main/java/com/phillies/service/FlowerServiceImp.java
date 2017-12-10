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
		Flower flower = new Flower();
		List<Flower> flowers = flowerRepo.findAll();
		for(Flower f:flowers) {
			if(f.getName().equalsIgnoreCase(name))
				flower = f;
		}
		return flower;
	}

	@Override
	public int updateFlowerStock(String name, boolean flag, int amount) {
		System.out.println(amount);
		Flower flower = getFlower(name);
		int stock = 0;
		if(flag)
			stock = flower.getStock()-amount;
		else
			stock = flower.getStock()+amount;
		flower.setStock(stock);
		flowerRepo.save(new Flower(flower.getId(), flower.getName(), flower.getStock(), flower.getPrice()));
		return flower.getStock();
	}

	@Override
	public int getStock(String name) {
		Flower flower = getFlower(name);
		return flower.getStock();
	}


}