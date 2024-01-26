package com.example.aopdemo.dao;

import com.example.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;

    @Override
    public void addAccount(Account account, boolean b) {
        System.out.println(getClass() + ": Adding account");
    }

    @Override
    public void doWork(boolean b) {
        System.out.println(getClass() + ": Doing work");
    }

    @Override
    public String getName() {
        System.out.println("getName");
        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println("setName");
        this.name = name;
    }

    @Override
    public String getServiceCode() {
        System.out.println("getCode");
        return serviceCode;
    }

    @Override
    public void setServiceCode(String serviceCode) {
        System.out.println("setCode");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        if (tripWire) {
            throw new RuntimeException("Test exception");
        }

        List<Account> accounts = new ArrayList<>();

        Account account1 = new Account("foo","bar");
        Account account2 = new Account("Justin","Bailey");

        accounts.add(account1);
        accounts.add(account2);

        return accounts;

    }
}
