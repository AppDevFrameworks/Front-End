package com.phillies.domain;

import javax.validation.constraints.Size;

import com.sun.istack.internal.NotNull;

public class OrderForm {

	@NotNull
	@Size(min=2, max=20, message="Input must be between 2 & 20 characters long")
	private String firstName;
	
	@NotNull
	@Size(min=2, max=20, message="Input must be between 2 & 20 characters long")
	private String lastName;

	//	@Min(value=0, message="Cannot be less than 0")
	//	@Max(value=100, message="Cannot be more than 100")
	//	private int age;

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
