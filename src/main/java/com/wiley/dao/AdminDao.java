package com.wiley.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wiley.beans.Admin;

public interface AdminDao extends JpaRepository<Admin, String> {


	@Query("select a from Admin a where id=:adminId and password=:pwd")
	Admin checkLogin(@Param("adminId") String adminId, @Param("pwd") String password);
}
