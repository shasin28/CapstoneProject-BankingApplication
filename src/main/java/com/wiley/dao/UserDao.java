package com.wiley.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wiley.beans.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {

	@Query("Select u from User u where userId=:userId and password=:password")
	User checkLogin(@Param("userId") String userId, @Param("password") String password);

//	public Account getAccountById(String userId);
}
