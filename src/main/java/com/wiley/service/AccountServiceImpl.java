package com.wiley.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.beans.Account;
import com.wiley.beans.Transaction;
import com.wiley.dao.AccountDao;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountDao accountDao;

	@Override
	public Account getAccountByNumber(long accountNumber){
		
		return accountDao.getUsingId(accountNumber);
		
//		Account account = new Account();
//		try {
//		account=accountDao.getById(accountNumber);
//		}catch(EntityNotFoundException e) {
//			return null;
//		}
//		return account;
	}

	@Override
	public double getBalance(long accountNumber) {
		Account account=accountDao.findById(accountNumber).get();
		if(account==null)
		{
			//handle account doesent't exist exception
		}
		return account.getBalance();
	}

	@Override
	public Account setBalance(long accountNumber, double balance) {
		Account account=accountDao.findById(accountNumber).get();//handle by query
		if(account==null)
		{
			//handle account doesent't exist exception
		}
		account.setBalance(balance);
		return account;
	}

	@Override
	public List<Transaction> getAllTransaction(long accountNumber) {
		List<Transaction> transactions=null;//accountDao.getAllTransaction(accountNumber);
		return transactions;
	}

	@Override
	public boolean insertAccount(Account account) {
		Account newAccount=accountDao.save(account);
		if(newAccount==null)
		{
			return false;
		}
		return true;
		
	}

	@Override
	public boolean updateAmount(Account account, double d) {
		int flag = accountDao.updateAmount(account.getAccountNumber(),d);
		if(flag>=1)
			return true;
		return false;
	}
	
	
	
}
