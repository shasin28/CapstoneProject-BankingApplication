package com.wiley.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import com.wiley.beans.Account;
import com.wiley.beans.Admin;
import com.wiley.beans.Card;
import com.wiley.beans.Employee;
import com.wiley.beans.Transaction;
import com.wiley.beans.Upi;
import com.wiley.beans.User;
import com.wiley.exceptions.AdminNotFoundException;
import com.wiley.exceptions.EmployeeNotFoundException;
import com.wiley.exceptions.UserNotFoundException;
import com.wiley.service.AccountService;
import com.wiley.service.AdminService;
import com.wiley.service.CardService;
import com.wiley.service.EmployeeService;
import com.wiley.service.TransactionService;
import com.wiley.service.UpiService;
import com.wiley.service.UserService;


@Controller
@SessionAttributes({"user"})
public class BankingController {
	@Autowired
	AccountService accountService;
	@Autowired
	UserService userService;
	@Autowired
	TransactionService transactionService;
	@Autowired
	CardService cardService;
	@Autowired
	UpiService upiService;

	@RequestMapping("/")
	public ModelAndView showMenuController() {
		return new ModelAndView("index");
	}
	@RequestMapping("/userLoginPage")
	public ModelAndView showLoginController() {
		return new ModelAndView("userLogin");
	}
	@RequestMapping("/login")
	public ModelAndView showUserLoginController() {
		return new ModelAndView("userPage");
	}

//	@RequestMapping("/makePayment")
//	public ModelAndView showPaymentController() {
//		return new ModelAndView("payment");
//	}
	@RequestMapping("/addBalance")
	public ModelAndView addAmountController()
	{
		return new ModelAndView("addAmount");
	}
	@RequestMapping("/showDetails")
	public ModelAndView showDetailsController() {
		return new ModelAndView("userPage","details",1);
	}
	
	
	@RequestMapping("/goBack")
	public ModelAndView goBackPageController()
	{
		return new  ModelAndView("userPage");
	}
	
	
	@RequestMapping("/byCard")
	public ModelAndView payByCardPageController()
	{
		return new  ModelAndView("payByCard");
	}
	@RequestMapping("/byUpi")
	public ModelAndView payByUpiPageController()
	{
		return new  ModelAndView("payByUpi");
	}
	@RequestMapping("/userLogin")
	public ModelAndView userLoginController(@RequestParam("userId") String userId, @RequestParam("password") String password,HttpSession session) {
		ModelAndView mv=new ModelAndView();
		try {
		User user = userService.checkLoginDetails(userId, password);
		if(user==null)
			throw new UserNotFoundException("User Not found");
		mv.addObject("user", user);

		mv.setViewName("userPage");//setting for another page
		}
		catch(UserNotFoundException e) {
			return new ModelAndView("userLogin","msg",-1);//same page with user not found msg
		}
		session.invalidate();
		return mv;//return to another page
	}
	@RequestMapping("/cardPay")
	public ModelAndView payByCardController(@RequestParam("cardNumber") long cardnumber,@RequestParam("cvv") int cvv)
	{
		ModelAndView mv= new ModelAndView("payment","mode","Card");
		Card card= cardService.getCardByDetails(cardnumber, cvv);
		if(card!=null)
		{
//			mv.setViewName("payment");
			mv.addObject("command", new Account());
			return mv;
		}
		else
		{
//			mv.setViewName("payByCard");
//			mv.addObject("msg", -1);
//			return mv;
			return new ModelAndView("payByCard","msg",-1);
		}
		
	}
	@RequestMapping("/upiPay")
	public ModelAndView payByUpiController(@RequestParam("upiId") String upiId,@RequestParam("pin") int pin)
	{
		ModelAndView mv= new ModelAndView("payment","mode","UPI");
		Upi upi = upiService.getUpiByDetails(upiId, pin);
		if(upi!=null)
		{
//			mv.setViewName("payment");
			mv.addObject("command", new Account());
			return mv;
		}
		else
		{
//			mv.setViewName("payByUpi");
//			mv.addObject("msg", -1);
//			return mv;
			return new ModelAndView("payByUpi","msg",-1);
		}
		
		
	}
	
	@RequestMapping("/Paymentform")
	public ModelAndView makePaymentController(@ModelAttribute("command") Account a, HttpSession http,@RequestParam("mode") String mode) {

		ModelAndView mv = new ModelAndView("userPage","msg",1);
		User user = (User) http.getAttribute("user");
	
		Account account = user.getAccount();
		Account destinationAccount = accountService.getAccountByNumber(a.getAccountNumber());
		if(user.getAccount().getBalance()<a.getBalance())
			return new ModelAndView("payment", "msg",-1);
		else if(destinationAccount==null)
			return new ModelAndView("payment","msg",-2);
		else if(a.getAccountNumber() ==account.getAccountNumber())
			return new ModelAndView("payment","msg",-4);
		else if(a.getBalance()<=0)
			return new ModelAndView("payment","msg",-5);
		int id = transactionService.getMaxId();
		
		Transaction transaction = new Transaction(++id, a.getBalance(), account,destinationAccount,mode);
		boolean flag = transactionService.insert(transaction);
		double totalAmount=0;
		if(flag){
			totalAmount = accountService.getBalance(account.getAccountNumber());//account.getBalance()-amount;
			
			boolean flag1= accountService.updateAmount(account, totalAmount-a.getBalance());
			account.setBalance(totalAmount-a.getBalance());
			totalAmount = accountService.getBalance(a.getAccountNumber())+a.getBalance();//destinationAccount.getBalance()+amount;
			boolean flag2=accountService.updateAmount(destinationAccount,totalAmount);
			if(flag1&&flag2) {
				user.setAccount(account);
				mv.addObject("user",user);
				return mv;
			}
		}
		return new ModelAndView("payment","msg",-3);
	}
	@RequestMapping("/amount")
	public ModelAndView addAmountController(@RequestParam("balance") double amount,HttpSession http)
	{
		ModelAndView mv = new ModelAndView("addAmount","amount",1);
		User user = (User) http.getAttribute("user");
		if(amount<=0)
			return new ModelAndView("addAmount","amount",-2);
		Account account = user.getAccount();
		double totalAmount = accountService.getBalance(account.getAccountNumber());
		double updatedAmount=totalAmount+amount;
		boolean flag=accountService.updateAmount(account,updatedAmount );
		if(flag)
		{
			//accountService.setBalance(account.getAccountNumber(), updatedAmount);
			System.out.println(user);
			account.setBalance(updatedAmount);
			user.setAccount(account);
			mv.addObject("user",user);
			http.setAttribute("user", user);
			return mv;
		}
		return new ModelAndView("addAmount", "amount",-1);
	}
	
	@RequestMapping("/logout")
	public ModelAndView userlogoutController(SessionStatus status) {
		status.setComplete();
		return new ModelAndView("index");
		
	}
	
	
	@RequestMapping("/getTransactions")
	public ModelAndView historyController(HttpSession http) {
		User user=(User) http.getAttribute("user");
		//User user1 = userService.getUserBy(user.getUserId());
		Account account = user.getAccount();
		//System.out.println(account);
		List<Transaction> transactions = transactionService.getTrasactionsById(account);
		System.out.println(transactions);
		if(transactions.isEmpty())
			return new ModelAndView("userPage","transactions",1);//same page showing no transactions
		return new ModelAndView("userPage","transactions",transactions);//differnt page showing transactions
	}

}
