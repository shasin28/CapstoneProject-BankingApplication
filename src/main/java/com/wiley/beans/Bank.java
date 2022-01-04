package com.wiley.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Bank {

	@Id
	String ifscCode;
	@ManyToMany
	List<User> users;
	@ManyToMany
	List<Employee> employees;
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	public Bank(String ifscCode, List<User> users, List<Employee> employees) {
		super();
		this.ifscCode = ifscCode;
		this.users = users;
		this.employees = employees;
	}
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	} 
	
}
