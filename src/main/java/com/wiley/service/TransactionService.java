package com.wiley.service;

import java.util.List;

import com.wiley.beans.Account;
import com.wiley.beans.Transaction;

public interface TransactionService {

	public List<Transaction> getAllTransaction();
	public Transaction searchById(long id);
	public boolean insert(Transaction transactionInput);
	public List<Transaction> getTrasactionsById(Account userId);
	public int getMaxId();
}
