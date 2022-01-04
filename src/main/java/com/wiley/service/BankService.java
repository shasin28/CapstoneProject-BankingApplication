package com.wiley.service;

import java.util.List;

import com.wiley.beans.Bank;
import com.wiley.beans.Employee;
import com.wiley.beans.User;

public interface BankService {

	public List<User> getAllUsers();
	public List<Employee> getAllEmployees();
	public Bank getBankByIfsc(String ifsc);
}
