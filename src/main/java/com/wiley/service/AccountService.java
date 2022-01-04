package com.wiley.service;

import java.util.List;

import com.wiley.beans.Account;
import com.wiley.beans.Transaction;
import com.wiley.exceptions.AccountNotFoundException;

public interface AccountService {

	public Account getAccountByNumber(long accountNumber);
	public double getBalance(long accountNumber);
	public Account setBalance(long accountNumber,double balance);
	public List<Transaction> getAllTransaction(long accountNumber);
	public boolean insertAccount(Account account);
	public boolean updateAmount(Account account, double d);

}
