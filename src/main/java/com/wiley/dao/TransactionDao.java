package com.wiley.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wiley.beans.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Long> {

	@Query("select max(id) from Transaction")
	int getMaxId();

}
