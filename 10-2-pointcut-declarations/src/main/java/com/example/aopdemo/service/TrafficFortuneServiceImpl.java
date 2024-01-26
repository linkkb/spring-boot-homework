package com.example.aopdemo.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService{

    @Override
    public String getFortune() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "good fortune";
    }

    @Override
    public String getFortune(boolean tripwire) {
        if (tripwire) throw new RuntimeException("Highway is closed!");
        return getFortune();
    }
}
