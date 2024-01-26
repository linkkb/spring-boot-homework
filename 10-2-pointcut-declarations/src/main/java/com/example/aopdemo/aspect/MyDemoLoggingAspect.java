package com.example.aopdemo.aspect;

import com.example.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
    // This is where logging advices go
    // Starting with @Before
    //@Before("execution(* add*())") //0 parameters
    //@Before("execution(* add*(com.example.aopdemo.Account))") //Account parameter
    //@Before("execution(* add*(com.example.aopdemo.Account, ..))") //Account + 0 or more parameters
    //@Before("execution(* add*(..))") //Any parameters
    //@Before("execution(* com.example.aopdemo.dao.*.*(..))") //any parameters, any method, any class in given package

    @Before("com.example.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("======>>> Executing @Before advice 1 on dao method()");

        // display the method signature
        MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("======>>> " + methodSig);

        // display args
        Object[] args = theJoinPoint.getArgs();
        for (Object tempArg:args) { System.out.println(tempArg); }

    }

    //Adding afterReturning advice
    @AfterReturning(
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountAdvice(
            JoinPoint theJoinPoint,
            List<Account> result) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("======>>> Executing @AfterReturning advice on " + method);

        System.out.println("======>>> Result is " + result);

        Account tempAccount = result.get(0);
        tempAccount.setName("Goofy Name");
    }

    @AfterThrowing(
            pointcut="execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing="theException")
    public void afterThrowingAdvice(
            JoinPoint theJoinPoint, Throwable theException
    ) {
        //print out which method advice is applied on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("======>>> Executing @AfterThrowing advice on " + method);

        //log the exception
        System.out.println("======>>> Exception is " + theException);
    }

    @After("execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("======>>> Executing @After (finally) advice on " + method);
    }

    @Around("execution(* com.example.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("======>>> Executing @Around advice on " + method);

        long begin = System.currentTimeMillis();
        Object result = null;
        try {
            result = theProceedingJoinPoint.proceed();
        } catch (Throwable t) {
            //handle exception
            System.out.println("handling exception: " + t);
            // ignore exception
            //result = "Terrible luck";

            //rethrow exception
            throw t;
        }

        long end = System.currentTimeMillis();
        long duration = end - begin;
        System.out.println("@Around returns with duration " + duration / 1000.0);
        return result;
    }

}
