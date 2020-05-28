package com.cg.wallet.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.wallet.entity.Login;
import com.cg.wallet.entity.WalletAccount;
import com.cg.wallet.entity.WalletTransactions;
import com.cg.wallet.exception.WalletAccountException;
import com.cg.wallet.service.WalletAccountService;

@RestController
@CrossOrigin("*")
public class WalletAccountController {
	
	@Autowired
	WalletAccountService walletAccountService;
	
	@GetMapping("admin/login/{user}/{pass}")
	public ResponseEntity<Login>  findUserLogin(@PathVariable("user") String username, @PathVariable("pass") String password) throws WalletAccountException
	{
	  
		 Login log = walletAccountService.findUser(username,password);
		 if(log==null) {
			 throw new WalletAccountException("Login not successful");
		 }
		 return new ResponseEntity<Login>(log,HttpStatus.OK);
	
	}
	
	@GetMapping("walletAccount/{id}")
	public ResponseEntity<WalletAccount> findAccountById(@PathVariable("id")int id) throws WalletAccountException
	{
		WalletAccount account=walletAccountService.findAccountById(id);
		return new ResponseEntity<WalletAccount>(account,HttpStatus.OK);


	}

	@GetMapping("walletAccount")
	public ResponseEntity<List<WalletAccount>> findAllAccounts() throws WalletAccountException
	{
		List<WalletAccount> list = walletAccountService.findAllAccounts();
		return new ResponseEntity<List<WalletAccount>>(list,HttpStatus.OK);
	}
	
	
	@PutMapping("walletAccount/id/{id}/amount/{amount}")
	public ResponseEntity<WalletAccount> withdraw(@PathVariable("id")int id,@PathVariable("amount")double amount) throws WalletAccountException
	{
		WalletAccount account=walletAccountService.withdraw(id, amount);
		return new ResponseEntity<WalletAccount>(account,HttpStatus.OK);
	}
	
	

	@GetMapping("walletTransactions/{id}")
	public ResponseEntity<List<WalletTransactions>> printAllTransactionsById(@PathVariable("id") int id) throws WalletAccountException
	{
		List<WalletTransactions> list=walletAccountService.printAllTransactionsById(id);
		return new ResponseEntity<List<WalletTransactions>>(list,HttpStatus.OK);
	}
	
	
	@PutMapping("walletAccount/id/{id1}/id/{id2}/amount/{amount}")
	public ResponseEntity<WalletAccount> fundTransfer(@PathVariable("id1")int id1,@PathVariable("id2")int id2,@PathVariable("amount")double amount) throws WalletAccountException
	{
		WalletAccount walletAccount=walletAccountService.fundTransfer(id1,id2,amount);
		return new ResponseEntity<WalletAccount>(walletAccount,HttpStatus.OK);
	}
			

}
