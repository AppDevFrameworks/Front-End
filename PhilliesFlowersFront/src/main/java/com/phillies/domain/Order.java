package com.phillies.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {
	@Id
	int orderId;

	@NotNull
	@Size(min=2,max=30)
	private String firstName;
	
	@NotNull
	@Size(min=2,max=30)
	private String lastName; 
	
	@NotNull
	@Email(message = "Email should be valid")
	@Length(min=6)
	private String emailAddress;
	
	@Size(min=10,max=10)
	private String mobileNo;
	
	private String packageName;
	private String date;
	private float price;

	public Order(int orderId, String firstName, String lastName, String emailAddress, String mobileNo,
			String packageName, String date, float price) {
		super();
		this.orderId = orderId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.mobileNo = mobileNo;
		this.packageName = packageName;
		this.price = price;
		this.date = date;
	}

	public Order() {
		super();
	}
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
	
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
}