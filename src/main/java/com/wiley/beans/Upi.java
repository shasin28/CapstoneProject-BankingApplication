package com.wiley.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Upi {

	@Id
	String upiId;
	int pin;
	public Upi(String upiId, int pin) {
		super();
		this.upiId = upiId;
		this.pin = pin;
	}
	
	public Upi(String upiId) {
		super();
		this.upiId = upiId;
	}

	public String getUpiId() {
		return upiId;
	}
	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public Upi() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
