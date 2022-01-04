package com.wiley.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Admin {

	
	String password;
	@Id
	String id;
	@OneToOne
	Bank bank;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public Admin(String password, String id, Bank bank) {
		super();
		this.password = password;
		this.id = id;
		this.bank = bank;
	}
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Admin( String id,String password) {
		super();
		
		this.id = id;
		this.password = password;
	}
	
}
