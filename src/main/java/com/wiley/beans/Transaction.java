package com.wiley.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Transaction {

	@Id
	long id;//Generate autoValue
	private String transactionDate;
	double amount;
	String mode;
	@OneToOne
	Account source;
	@OneToOne
	Account destination;
	public Transaction(long id, String date, double amount, Account source, Account destination) {
		super();
		this.id = id;
		transactionDate = date;
		this.amount = amount;
		this.source = source;
		this.destination = destination;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDate() {
		return transactionDate;
	}
	public void setDate(String date) {
		transactionDate = date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Account getSource() {
		return source;
	}
	public void setSource(Account source) {
		this.source = source;
	}
	public Account getDestination() {
		return destination;
	}
	public void setDestination(Account destination) {
		this.destination = destination;
	}
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(long id, double amount, Account source, Account destination,String mode) {
		super();
		this.mode=mode;
		this.id =id;
		this.amount = amount;
		this.source = source;
		this.destination = destination;
	}
	
	
}
