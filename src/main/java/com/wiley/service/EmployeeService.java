package com.wiley.service;

import java.util.ArrayList;

import com.wiley.beans.Employee;

import antlr.collections.List;

public interface EmployeeService {

	public Employee getEmployeeById(String id);
	public Employee checkEmployeeDetails(String id,String password);
	public boolean insertEmployee(Employee e);
	public ArrayList<Employee> getAllEmployee();
}
