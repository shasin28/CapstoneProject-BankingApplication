package com.wiley.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.beans.Employee;
import com.wiley.dao.EmployeeDao;
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	@Override
	public Employee getEmployeeById(String id) {
		Employee employee=employeeDao.findById(id).get();
		if(employee==null)
		{
			//handle employee not present
		}
		return employee;
	}
	@Override
	public Employee checkEmployeeDetails(String id, String password) {
		
		return employeeDao.checkEmployeeDetails(id, password);
	}
	@Override
	public boolean insertEmployee(Employee e) {
		Employee emp=employeeDao.save(e);
		if(emp!=null)
			return true;
		
		return false;
	}
	@Override
	public ArrayList<Employee> getAllEmployee() {
		
		return (ArrayList<Employee>) employeeDao.findAll();
	}

}
