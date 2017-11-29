package com.phillies.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FlowerPackage {
	@Id
	int id;
	String name;
	String[] flowers;
	String[] items;
	
	int stock;
	float price;
	
	public FlowerPackage() {
		
	}
	
	public FlowerPackage(int id, String name, String[] flowers, String[] items, int stock, float price) {
		super();
		this.id = id;
		this.name = name;
		this.flowers = flowers;
		this.items = items;
		this.stock = stock;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String[] getFlowers() {
		return flowers;
	}

	public void setFlowers(String[] flowers) {
		this.flowers = flowers;
	}

	public String[] getItems() {
		return items;
	}

	public void setItems(String[] items) {
		this.items = items;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}