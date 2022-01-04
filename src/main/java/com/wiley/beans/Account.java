package com.wiley.beans;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Account {

	@Id
	long accountNumber;
	String name;
	double balance;
	@OneToOne
	Card card;
	@OneToOne
	Upi upi;
	@ManyToMany
	List<Transaction> transactions;
	
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public Upi getUpi() {
		return upi;
	}
	public void setUpi(Upi upi) {
		this.upi = upi;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public Account(long accountNumber, String name, double balance, Card card, Upi upi, List<Transaction> transactions) {
		super();
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
		this.card = card;
		this.upi = upi;
		this.transactions = transactions;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
public Account(long accountNumber, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
public Account(long accountNumber, String name, double balance, Card card, Upi upi) {
		super();
		this.accountNumber = accountNumber;
		this.name = name;
		this.balance = balance;
		this.card = card;
		this.upi = upi;
	}
//	@Override
//	public boolean equals(Object obj) {
//		// TODO Auto-generated method stub
//		return super.equals(obj);
//	}
	@Override
	public String toString() {
		return Long.toString(accountNumber);
	}
	
	
}
