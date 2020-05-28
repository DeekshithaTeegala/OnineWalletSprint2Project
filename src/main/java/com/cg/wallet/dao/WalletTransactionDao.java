package com.cg.wallet.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.wallet.entity.WalletAccount;
import com.cg.wallet.entity.WalletTransactions;
@Repository
public interface WalletTransactionDao extends JpaRepository<WalletTransactions,Integer>
{

}
