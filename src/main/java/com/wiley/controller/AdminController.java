package com.wiley.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.wiley.beans.Admin;
import com.wiley.beans.Employee;
import com.wiley.beans.User;
import com.wiley.exceptions.AdminNotFoundException;
import com.wiley.service.AdminService;
import com.wiley.service.EmployeeService;
import com.wiley.service.UpiService;
import com.wiley.service.UserService;

@Controller
@SessionAttributes({"admin"})
public class AdminController {
	@Autowired
	AdminService adminService;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	UserService userService;
	
	
	@RequestMapping("/adminCheck")
	public ModelAndView showAdminLoginController() {
		return new ModelAndView("adminPage");
	}
	@RequestMapping("/adminLoginPage")
	public ModelAndView showAdminController() {
		return new ModelAndView("adminLogin");
	}
	
	@RequestMapping("/adminLogin")
	public ModelAndView adminLoginController(@RequestParam("id") String adminId, @RequestParam("password") String password,HttpSession session)
	{
		ModelAndView mv= new ModelAndView();
		try
		{
			Admin admin= adminService.checkLoginDetails(adminId, password);
			if(admin==null)
				throw new AdminNotFoundException("Admin not found");
			mv.addObject("admin",admin);
			mv.setViewName("adminPage");	
		}
		catch(AdminNotFoundException a)
		{
			return new ModelAndView("adminLogin","msg",-1);
		}
		session.invalidate();
		return mv;
		
	}
	@RequestMapping("/insertEmployeePage")
	public ModelAndView insertEmployeetoPage()
	{
		return new ModelAndView("insertEmployee","command",new Employee());
	}
	@RequestMapping("/insertEmp")
	public ModelAndView insertEmployeeController(@ModelAttribute("command") Employee e)
	{
		if(employeeService.insertEmployee(e))
		{
			return new ModelAndView("insertEmployee","msg","Inserted successfully");
			
		}
		return new ModelAndView("insertEmployee","msg","Not Inserted successfully");
	}

	@RequestMapping("/showEmployeePage")
	public ModelAndView showEmployees(HttpSession http)
	{
		Admin admin=(Admin)http.getAttribute("admin");
		ArrayList<Employee> employees=employeeService.getAllEmployee();
		if(employees.isEmpty())
		{
			return new ModelAndView("adminPage","employees",1);
		}
		return new ModelAndView("adminPage","employees",employees);
	}
	@RequestMapping("/showUserPage")
	public ModelAndView showUsers(HttpSession http)
	{
		Admin admin=(Admin)http.getAttribute("admin");
		ArrayList<User> users=userService.getAllUsers();
		if(users.isEmpty())
		{
			return new ModelAndView("adminPage","users",1);
		}
		return new ModelAndView("adminPage","users",users);
	}
	@RequestMapping("/logoutAdmin")
	public ModelAndView adminlogoutController(SessionStatus status) {
		status.setComplete();
		return new ModelAndView("index");
		
	}
}
