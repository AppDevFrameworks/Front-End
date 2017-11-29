package com.phillies.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Flower {
	@Id
	int id;

	String name;
	int stock;
	float price;

	public Flower() {
		super();
	}

	public Flower(int id, String name, int stock, float price) {
		super();
		this.id = id;
		this.name = name;
		this.stock = stock;
		this.price = price;
	}
	
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
}
