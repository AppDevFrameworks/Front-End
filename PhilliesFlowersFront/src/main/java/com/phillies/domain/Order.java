package com.phillies.domain;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {
	@Id
	int orderId;

	String firstName,lastName,emailAddress,mobileNo, packageName;
	ArrayList<Flower> order = new ArrayList<>();
	float price;

	public Order(int orderId, String firstName, String lastName, String emailAddress, String mobileNo,
			String packageName, ArrayList<Flower> order, float price) {
		super();
		this.orderId = orderId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.mobileNo = mobileNo;
		this.packageName = packageName;
		this.order = order;
		this.price = price;
	}

	public Order() {
		super();
	}
	
	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public ArrayList<Flower> getOrder() {
		return order;
	}
	
	public void setOrder(ArrayList<Flower> order) {
		this.order = order;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
}