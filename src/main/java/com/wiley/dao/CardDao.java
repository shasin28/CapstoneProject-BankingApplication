package com.wiley.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.wiley.beans.Card;

@Repository
public interface CardDao extends JpaRepository<Card, Long> {
	@Query("select c from Card c where cardNumber=:cardnum")
	Card getCardById(@Param("cardnum") long cardNum);
	@Query("select c from Card c where cardNumber=:cardnum and cvv=:cvv")
	Card getCardByDetails(@Param("cardnum") long cardNum,@Param("cvv") int cvv);

}
