package com.example.springAOP.dao;

import com.example.springAOP.Account;

import java.util.List;

public interface AccountDao {
    void addAccount(Account account);

    void updateAccount();

    List<Account> findAccounts();

    List<Account> findAccounts(Boolean tripWire);

    public long getAmount();
    public void setAmount(long amount);
    public String getAccHolderName();
    public void setAccHolderName(String accHolderName);

}
