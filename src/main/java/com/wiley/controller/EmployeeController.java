package com.wiley.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.wiley.beans.Account;
import com.wiley.beans.Card;
import com.wiley.beans.Employee;
import com.wiley.beans.Upi;
import com.wiley.beans.User;
import com.wiley.exceptions.EmployeeNotFoundException;
import com.wiley.service.AccountService;
import com.wiley.service.CardService;
import com.wiley.service.EmployeeService;
import com.wiley.service.UpiService;
import com.wiley.service.UserService;

@Controller
@SessionAttributes ({"employee","card","upi","account"})
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	UserService userService;
	@Autowired
	CardService cardService;
	@Autowired
	UpiService upiService;
	@Autowired 
	AccountService accountService;
	
	
	@RequestMapping("/employeeLoginPage")
	public ModelAndView showEmployeeController()
	{
		return new ModelAndView("employeeLogin");
	}
	@RequestMapping("/employeeCheck")
	public ModelAndView showEmployeeLoginController()
	{
		return new ModelAndView("employeePage");
	}
	
	@RequestMapping("/employeeLogin")
	public ModelAndView employeeLoginController(@RequestParam("id") String employeeId, @RequestParam("password") String password)
	{
		ModelAndView mv=new ModelAndView();
		try
		{
			Employee employee=employeeService.checkEmployeeDetails(employeeId, password);
			if(employee==null)
				throw new EmployeeNotFoundException("Employee not found");
			mv.addObject("employee",employee);
			mv.setViewName("employeePage");
		}
		catch(EmployeeNotFoundException e)
		{
			return new ModelAndView("employeeLogin","msg",-1);
		}
		return mv;
	}
	@RequestMapping("/showUsers")
	public ModelAndView showEmployeesInBankController(HttpSession session)
	{
		Employee emp=(Employee)session.getAttribute("employee");
		ArrayList<User> users=userService.getAllUsers();
		if(users.isEmpty())
			
			return new ModelAndView("employeePage","users",1);
		
		
		return new ModelAndView("employeePage","users",users);
	}

	@RequestMapping("/insertUsers")
	public ModelAndView createCardPageController()
	{
		return new ModelAndView("insertCard","command",new Card());
	}
	@RequestMapping("/createCard")
	public ModelAndView createCardController(@ModelAttribute("command") Card c)
	{
		ModelAndView mv= new ModelAndView();
		
		if(cardService.insertCard(c))
		{
			mv.setViewName("createUpi");
			mv.addObject("command", new Upi());
			mv.addObject("card", c);
			return mv;
		}
		if(cardService.getCardByNumber(c.getCardNumber()) != null)
		{
			mv.setViewName("insertCard");
			mv.addObject("msg", -1);
			return mv;
		}
		mv.setViewName("insertCard");
		mv.addObject("msg", -2);
		return mv;	
		
	}
	@RequestMapping("/createupi")
	public ModelAndView createUpiController(@ModelAttribute("command") Upi u)
	{
		ModelAndView mv= new ModelAndView();
		if(upiService.createUpi(u))
		{
			mv.setViewName("createAccount");
			mv.addObject("command", new Account());
			mv.addObject("upi", u);
			return mv;
		}

		if(upiService.getUpiById(u.getUpiId())!=null)
		{
			mv.setViewName("createUpi");
			mv.addObject("msg",-1);
			return mv;
			
		}
		mv.setViewName("createUpi");
		mv.addObject("msg",-2);
		return mv;
	}
	@RequestMapping("/createAcc")
	public ModelAndView createAccountController(@ModelAttribute("command") Account a,HttpSession http)
	{
		ModelAndView mv= new ModelAndView();
		Card card=(Card) http.getAttribute("card");
		Upi upi=(Upi)http.getAttribute("upi");
		
		Account account= new Account(a.getAccountNumber(),card.getName(),a.getBalance(),card,upi);
		if(accountService.insertAccount(account))
		{
			mv.setViewName("createUser");
			mv.addObject("command", new User());
			mv.addObject("account",account);
			return mv;
		}
			
		if(accountService.getAccountByNumber(a.getAccountNumber())!=null)
		{
			mv.setViewName("createAccount");
			mv.addObject("msg",-1);
			return mv;
		}
		mv.setViewName("createAccount");
		mv.addObject("msg",-2);
		return mv;
	}
	@RequestMapping("/createUser")
	public ModelAndView createUserController(@ModelAttribute("command") User user,HttpSession http)
	{
		ModelAndView mv= new ModelAndView();
		Card card=(Card) http.getAttribute("card");
		Account account=(Account)http.getAttribute("account");
		User userr= new User(user.getUserId(),card.getName(),user.getPassword(),account);
		if(userService.insertUser(userr))
		{
			mv.setViewName("createUser");
			mv.addObject("msg",1);
			return mv;
		}
		if(userService.getUserBy(userr.getUserId())!=null)
		{
			mv.setViewName("createUser");
			mv.addObject("msg",-1);
			return mv;
			
		}
		mv.setViewName("createUser");
		mv.addObject("msg",-2);
		return mv;
	}
	@RequestMapping("/logoutEmp")
	public ModelAndView employeelogoutController(SessionStatus status) {
		status.setComplete();
		return new ModelAndView("index");
		
	}
}
