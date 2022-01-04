package com.wiley.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wiley.beans.Account;
import com.wiley.beans.Transaction;
import com.wiley.dao.TransactionDao;
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao transactionDao;
	@Override
	public List<Transaction> getAllTransaction() {
		List<Transaction> transactionList=transactionDao.findAll();
		return transactionList;
	}

	@Override
	public Transaction searchById(long id) {
		Transaction transaction1=transactionDao.findById(id).get();
		if(transaction1==null)
		{
			//No Record Present
		}
		return transaction1;
	}
	//can handle normally also in controller
	@Override
	public boolean insert(Transaction transactionInput) {
		 LocalDateTime myDateObj = LocalDateTime.now();
		 DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		 String formattedDate = myDateObj.format(myFormatObj);
		 transactionInput.setDate(formattedDate);
		 Transaction t=transactionDao.save(transactionInput);
	     if(t==null)
	    	return false;
	     return true;
		
	}

	@Override
	public List<Transaction> getTrasactionsById(Account account) {
		List<Transaction> transactionList=transactionDao.findAll();
		ArrayList<Transaction> transactions = new ArrayList<Transaction>();
		for(Transaction transaction:transactionList) {
			if(transaction.getSource().getAccountNumber()==account.getAccountNumber()||transaction.getDestination().getAccountNumber()==account.getAccountNumber())
				transactions.add(transaction);
		}
		return transactions;
	}

	@Override
	public int getMaxId() {
		if(transactionDao.getMaxId()>0)
			return transactionDao.getMaxId();
		else
			return 0;
	}

}
