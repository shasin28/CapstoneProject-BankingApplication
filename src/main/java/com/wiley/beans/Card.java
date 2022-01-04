package com.wiley.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Card {
	@Id
	long cardNumber;
	int cvv;
	String expiryDate;
	String name;
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Card(long cardNumber, int cvv, String expiryDate, String name) {
		super();
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.expiryDate = expiryDate;
		this.name = name;
	}
	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Card(long cardNumber, int cvv) {
		super();
		this.cardNumber = cardNumber;
		this.cvv = cvv;
	}
	
	
//	@Override
//	public boolean equals(Object obj) {
//		// TODO Auto-generated method stub
//		return super.equals(obj);
//	}
	
	
}
