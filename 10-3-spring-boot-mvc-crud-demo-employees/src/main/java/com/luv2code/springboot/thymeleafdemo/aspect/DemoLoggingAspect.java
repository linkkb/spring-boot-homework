package com.luv2code.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {
    //setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    public void forControllerPackage() {}
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    public void forServicePackage() {}
    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    public void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    public void forAppFlow() {}

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {
        //display method and arguments
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info(">>> in @Before: calling method: " + theMethod);
        Object[] args = theJoinPoint.getArgs();
        for(Object tempArg:args) {
            myLogger.info(">>> argument: " + tempArg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult" //must match method arg name
    )
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
        //display method and arguments
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info(">>> in @after: from method: " + theMethod);
        myLogger.info(">>> result = " + theResult);
    }
}
