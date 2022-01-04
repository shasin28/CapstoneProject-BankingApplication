package com.wiley.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wiley.beans.Bank;
import com.wiley.beans.Employee;
import com.wiley.beans.User;

@Repository
public interface BankDao extends JpaRepository<Bank, String> {

//	public List<User> getAllUsers();
//	public List<Employee> getAllEmployees();
	
}
