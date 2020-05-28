package com.cg.wallet.service;

import java.util.List;

import com.cg.wallet.entity.Login;
import com.cg.wallet.entity.WalletAccount;
import com.cg.wallet.entity.WalletTransactions;
import com.cg.wallet.exception.WalletAccountException;

public interface WalletAccountService {
	
	public Login findUser(String username , String password) throws WalletAccountException;

	public WalletAccount withdraw(int accountId,double amount) throws WalletAccountException;
	
	public WalletAccount fundTransfer(int accountId1,int accountId2,double amount) throws WalletAccountException;
	
	public List<WalletTransactions> printAllTransactionsById(int accountId) throws WalletAccountException;
	
	public List<WalletAccount> findAllAccounts() throws WalletAccountException;

	public WalletAccount  findAccountById(int accountId) throws WalletAccountException; 

 }

