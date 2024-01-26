package com.example.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public boolean addMembership() {
        System.out.println(getClass() + ": Adding membership account");
        return true;
    }

    @Override
    public void doWork(boolean b) {
        System.out.println(getClass() + ": Doing work");
    }
}
