package com.cg.wallet.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.wallet.dao.LoginDao;
import com.cg.wallet.dao.WalletAccountDao;
import com.cg.wallet.dao.WalletTransactionDao;
import com.cg.wallet.entity.Login;
import com.cg.wallet.entity.WalletAccount;
import com.cg.wallet.entity.WalletTransactions;
import com.cg.wallet.exception.WalletAccountException;

import oracle.sql.DATE;

@Service
public class WalletAccountServiceImpl implements WalletAccountService{
	
	@Autowired
	WalletAccountDao accountDao;
	
	@Autowired
	WalletTransactionDao transactionDao;
	
	@Autowired
	LoginDao loginDao;

	@Override
	public Login findUser(String username,String password) throws WalletAccountException {

		return loginDao.findUser(username,password);
	}
	
	@Override
	@Transactional
	public WalletAccount withdraw(int accountId,double amount) throws WalletAccountException {
		
		
		WalletAccount walletAccount=null;
		
		if(accountDao.existsById(accountId))
		{
		walletAccount=accountDao.findById(accountId).get();
		
		if(walletAccount.getAccountBal()<amount)
		{
			throw new WalletAccountException("Insufficient balance");
		}
		
		walletAccount.setAccountBal(walletAccount.getAccountBal()-amount);
		
		
		
		WalletTransactions transaction=new WalletTransactions();
	
		transaction.setDescription("withdraw");
		transaction.setAccountBal(walletAccount.getAccountBal());
		transaction.setAmount(amount);
		transaction.setDateOfTransaction(LocalDate.now());
		List<WalletTransactions> list = walletAccount.getTransactions();
		list.add(transaction);
		
		transaction.setWalletAccount(walletAccount);
     	transactionDao.saveAndFlush(transaction);
		accountDao.saveAndFlush(walletAccount);
		}
		else {
			throw new WalletAccountException("account Id not found");
		}
		return walletAccount;
	}
	
	@Override
	public List<WalletTransactions> printAllTransactionsById(int accountId) throws WalletAccountException {
	 List<WalletTransactions> list=null;
	
	 boolean flag=accountDao.existsById(accountId);
	 
	 if(flag)
	 {
		 WalletAccount account=accountDao.getOne(accountId);
		 list=account.getTransactions();

	 }
	 else
	 {
		 throw new WalletAccountException("id not found");
	 }
		
		return list;
	}
	
	@Override
	public WalletAccount fundTransfer(int accountId1,int accountId2,double amount) throws WalletAccountException {
		
		WalletAccount walletAccount1=null;
		WalletAccount walletAccount2=null;
		
//		if(amount>10000)
//		{
//			throw new WalletAccountException("amount should be less than 10000");
//		}
		boolean flag1=accountDao.existsById(accountId1);
		if(flag1)
		{
		walletAccount1=	accountDao.findById(accountId1).get();
	    withdraw(accountId1,amount);
		}
		else
		{
			throw new WalletAccountException("id not found");
		}
	    
		boolean flag2=accountDao.existsById(accountId2);
		if(flag2)
		{
		    walletAccount2=accountDao.findById(accountId2).get();
			walletAccount2.setAccountBal(walletAccount2.getAccountBal()+amount);
			WalletTransactions transaction=new WalletTransactions();
			
			transaction.setDescription("deposit");
			transaction.setAccountBal(walletAccount2.getAccountBal());
			transaction.setAmount(amount);
			transaction.setDateOfTransaction(LocalDate.now());
			List<WalletTransactions> list = walletAccount2.getTransactions();
			list.add(transaction);
			
			transaction.setWalletAccount(walletAccount2);
	     	transactionDao.saveAndFlush(transaction);
			accountDao.saveAndFlush(walletAccount2);
			
		}
		else
		{
			throw new WalletAccountException("id not found");
		}
		
		
	
		return walletAccount1;
	}
	
	
	
	@Override
	public WalletAccount findAccountById(int accountId) throws WalletAccountException {
		WalletAccount walletAccount=null;
		boolean flag=accountDao.existsById(accountId);
		
		if(flag)
		{
		
		   walletAccount=accountDao.findById(accountId).get();

		}
		else
		{
			throw new WalletAccountException("id not found");
		}
		
		
		 return walletAccount;

	}
	

	@Override
	public List<WalletAccount> findAllAccounts() throws WalletAccountException {
        List<WalletAccount> list=accountDao.findAll();
		
		return list;
	}
	
	

	




}