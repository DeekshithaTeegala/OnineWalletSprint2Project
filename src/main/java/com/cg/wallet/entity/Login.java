package com.cg.wallet.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login_tbl")
public class Login {
	
	@Id
	@Column(name="username",length=15)
	private String userName;
	
	@Column(name="password",length=15)
	private String passWord;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
	

}
