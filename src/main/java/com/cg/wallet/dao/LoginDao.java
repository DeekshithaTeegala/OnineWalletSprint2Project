package com.cg.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cg.wallet.entity.Login;



@Repository
public interface LoginDao extends JpaRepository<Login, String>{


	@Query(" FROM Login where username=:username1 and password=:pass1")
	public Login findUser(@Param("username1") String username,@Param("pass1") String password);
	
}
