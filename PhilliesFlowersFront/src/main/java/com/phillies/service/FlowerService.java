package com.phillies.service;

import com.phillies.domain.Flower;

public interface FlowerService {
	public Flower getFlower(String flower);
	public void updateFlowerStock(String flower);
}