package com.wiley.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wiley.beans.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

	@Transactional
	@Modifying
	@Query(value="UPDATE Account SET balance =:bal WHERE accountNumber =:cid")
	int updateAmount(@Param("cid")long accountNumber,@Param("bal") double d);

	@Query("Select a from Account a where id=:aid")
	Account getUsingId(@Param("aid")long accountNumber);

}
