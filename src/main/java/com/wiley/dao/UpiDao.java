package com.wiley.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wiley.beans.Upi;

@Repository
public interface UpiDao extends JpaRepository<Upi, String> {
	@Query("select u from Upi u where upiId=:id")
	Upi getUpiByid(@Param("id") String upiid);
	
	@Query("select u from Upi u where upiId=:id and pin=:pin")
	Upi getUpiByDetails(@Param("id") String upiid,@Param("pin") int pin);

}
