package com.cg;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.wallet.dao.WalletAccountDao;
import com.cg.wallet.dao.WalletTransactionDao;
import com.cg.wallet.entity.WalletAccount;
import com.cg.wallet.exception.WalletAccountException;
import com.cg.wallet.service.WalletAccountService;

@SpringBootTest
class OnlineWalletSprint2ApplicationTests  {

	@MockBean
	private WalletAccountDao accountDao;
	
	@MockBean
	private WalletTransactionDao transactionDao;
	
	@Autowired
	private WalletAccountService walletAccountService;
	

	
	@Test
	public void getAllAccountsTest() throws WalletAccountException{
		// we pass two accounts and if the method in service returns the same size
		when(accountDao.findAll())
				.thenReturn(Stream
						.of(new WalletAccount(2006,20000,"saving"),new WalletAccount(2007,25000,"saving"),
								new WalletAccount(2008,10000,"current")).collect(Collectors.toList()));
		assertEquals(3, walletAccountService.findAllAccounts().size());

	}


	
	
}


