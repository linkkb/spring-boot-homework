package com.example.aopdemo.dao;

import com.example.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {

    @Override
    public void addAccount(Account account, boolean b) {
        System.out.println(getClass() + ": Adding account");
    }

    @Override
    public void doWork(boolean b) {
        System.out.println(getClass() + ": Doing work");
    }
}
