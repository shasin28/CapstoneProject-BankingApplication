package com.wiley.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {

	@Id
	String userId;//can be email or phonenumber
	String name;
	String password;
	@OneToOne
	Account account;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPhoneNumber() {
		return userId;
	}
	public void setPhoneNumber(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public User(String userId, String name) {
		super();
		this.userId = userId;
		this.name = name;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userId, String name, String password, Account account) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.account = account;
	}
	
}
