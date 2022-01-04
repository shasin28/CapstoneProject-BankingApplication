package com.wiley.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.beans.Bank;
import com.wiley.beans.Employee;
import com.wiley.beans.User;
import com.wiley.dao.BankDao;
@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private BankDao bankDao;
	@Override
	public List<User> getAllUsers() {
		List<User> users=null;//bankDao.getAllUsers();
		if(users==null)
		{
			//handle exception
		}
		return users;
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employees=null;//bankDao.getAllEmployees();
		if(employees==null)
		{
			//handle Exception
		}
		return employees;
	}

	@Override
	public Bank getBankByIfsc(String ifsc) {
		// TODO Auto-generated method stub
		Bank bank=bankDao.getById(ifsc);
		if(bank==null)
		{
			//handle no bank exists
		}
		return bank;
	}
	

}
