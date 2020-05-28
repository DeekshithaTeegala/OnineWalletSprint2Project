package com.cg.wallet.entity;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="wallettrans_tbl")
public class WalletTransactions {

	@Id
	@Column(name="trans_id")
	@GeneratedValue(generator="mygen",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="mygen",sequenceName="transaction_seq",allocationSize=1)
	private  int transactionId;
	
	@Column(name="description",length=15)
	private String description;
	
	@Column(name="trxdate")
//	@Temporal(TemporalType.DATE)
	private LocalDate dateOfTransaction;
	

	@Column(name="trxamount")
	private double amount;
	
	@Column(name="accountbal")
	private double accountBal;
	
	@ManyToOne
    @JoinColumn(name="account_id")
    @JsonIgnore
	private WalletAccount walletAccount;
	
	
	
	public WalletTransactions(int transactionId, String description, LocalDate dateOfTransaction, double amount,
			double accountBal) {
		super();
		this.transactionId = transactionId;
		this.description = description;
		this.dateOfTransaction = dateOfTransaction;
		this.amount = amount;
		this.accountBal = accountBal;
		
	}

    

	public WalletTransactions() {
		super();
	
	}



	public int getTransactionId() {
		return transactionId;
	}



	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(LocalDate dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAccountBal() {
		return accountBal;
	}

	public WalletAccount getWalletAccount() {
		return walletAccount;
	}



	public void setWalletAccount(WalletAccount walletAccount) {
		this.walletAccount = walletAccount;
	}



	public void setAccountBal(double accountBal) {
		this.accountBal = accountBal;
	}
	
	
}
