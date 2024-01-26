package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
    // This is where logging advices go
    // Starting with @Before
    //@Before("execution(* add*())") //0 parameters
    //@Before("execution(* add*(com.example.aopdemo.Account))") //Account parameter
    //@Before("execution(* add*(com.example.aopdemo.Account, ..))") //Account + 0 or more parameters
    //@Before("execution(* add*(..))") //Any parameters
    @Before("execution(* com.example.aopdemo.dao.*.*(..))") //any parameters, any method, any class in given package
    public void beforeAddAccountAdvice() {
        System.out.println("======>>> Executing @Before advice on addAccount()");
    }

}
