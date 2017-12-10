package com.phillies.service;

import com.phillies.domain.Flower;

public interface FlowerService {
	public Flower getFlower(String flower);
	public int updateFlowerStock(String flower, boolean flag, int amount);
	public int getStock(String flower);
}