package com.example.springAOP.dao;

import com.example.springAOP.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDaoImpl implements AccountDao{

    private String accHolderName;

    private long amount;

    public long getAmount() {
        System.out.println(getClass().getSimpleName() + ": inside getAmount");
        return amount;
    }

    public void setAmount(long amount) {
        System.out.println(getClass().getSimpleName() + ": inside setAmount");
        this.amount = amount;
    }

    public String getAccHolderName() {
        System.out.println(getClass().getSimpleName() + ": inside getAccHolderName");
        return accHolderName;
    }

    public void setAccHolderName(String accHolderName) {
        System.out.println(getClass().getSimpleName() + ": inside setAccHolderName");
        this.accHolderName = accHolderName;
    }

    @Override
    public void addAccount(Account account) {
        System.out.println(getClass().getSimpleName() + ": Doing my db work, adding account");
    }

    @Override
    public void updateAccount() {
        System.out.println(getClass().getSimpleName() + ": Doing my db work, updating account");

    }


    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(Boolean tripWire) {

        if(tripWire){
            throw new RuntimeException("Afterthrowing demo exception");
        }

        List<Account> myAccounts = new ArrayList<>();

        // create sample accounts
        Account temp1 = new Account("John", "Silver");
        Account temp2 = new Account("Madhu", "Platinum");
        Account temp3 = new Account("Luca", "Gold");

        // add them to our accounts list
        myAccounts.add(temp1);
        myAccounts.add(temp2);
        myAccounts.add(temp3);

        return myAccounts;
    }

}
