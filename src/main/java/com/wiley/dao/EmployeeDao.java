package com.wiley.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.wiley.beans.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, String> {
	
	@Query("select e from Employee e where id=:id and password=:password")
	Employee checkEmployeeDetails(@RequestParam("id")String id,@RequestParam("password") String password);

}
