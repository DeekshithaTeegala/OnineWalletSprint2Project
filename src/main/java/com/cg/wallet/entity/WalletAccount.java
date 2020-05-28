package com.cg.wallet.entity;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="walletaccount_tbl")
public class WalletAccount {

	@Id
	@GeneratedValue(generator="mygen",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="mygen",sequenceName="account_seq",allocationSize=1)
	@Column(name="account_id")
	private int accountId;
	
	@Column(name="accountbal")
	private double accountBal;
	
	@Column(name="status",length=15)
	private String status;

	@OneToMany(mappedBy="walletAccount")
	private List<WalletTransactions> transactions=new ArrayList<>();
	
	

	public WalletAccount(int accountId, double accountBal, String status) {
		super();
		this.accountId = accountId;
		this.accountBal = accountBal;
		this.status = status;
		
	}

	

	public WalletAccount() {
		super();
	}

	@Transient
	private int countTransactions=transactions.size();
	
	public int getCountTransactions() {
		return transactions.size();
	}

	public List<WalletTransactions> getTransactions() {
		return transactions;
	}


	public void setTransactions(List<WalletTransactions> transactions) {
		this.transactions = transactions;
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	
	
	public double getAccountBal() {
		return accountBal;
	}

	public void setAccountBal(double accountBal) {
		this.accountBal = accountBal;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	



}
