package com.example.aopdemo.dao;

import com.example.aopdemo.Account;

public interface AccountDAO {
    void addAccount(Account account, boolean b);

    void doWork(boolean b);
}
