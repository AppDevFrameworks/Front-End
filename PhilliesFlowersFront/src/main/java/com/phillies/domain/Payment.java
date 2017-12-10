package com.phillies.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Payment {
	@Id
	int paymentId;
	
	@NotNull
	@Size(min=16,max=16)
	@CreditCardNumber
	String cardNo;
	
	@NotNull
	@Size(min=2,max=50)
	String cardholder;
	
	@NotNull
	@Size(min=2,max=2)
	int month;
	
	@NotNull
	@Size(min=4,max=4)
	int year;
	
	@NotNull
	@Size(min=3,max=3)
	int cvc;
	
	public Payment(int paymentId, String cardNo, String cardholder, int month, int year, int cvc) {
		super();
		this.paymentId = paymentId;
		this.cardNo = cardNo;
		this.cardholder = cardholder;
		this.month = month;
		this.year = year;
		this.cvc = cvc;
	}

	public Payment() {
		super();
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCardholder() {
		return cardholder;
	}

	public void setCardholder(String cardholder) {
		this.cardholder = cardholder;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getCvc() {
		return cvc;
	}

	public void setCvc(int cvc) {
		this.cvc = cvc;
	}
}