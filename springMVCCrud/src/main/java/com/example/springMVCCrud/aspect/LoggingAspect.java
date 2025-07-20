package com.example.springMVCCrud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    //setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    //setup pointcut declarations
    @Pointcut("execution(* com.example.springMVCCrud.controller.*.*(..))")
    private void forControllerPackage() { }

    @Pointcut("execution(* com.example.springMVCCrud.service.*.*(..))")
    private void forServicePackage() { }

    @Pointcut("execution(* com.example.springMVCCrud.dao.*.*(..))")
    private void forDaoPackage() { }

    @Pointcut("forControllerPackage || forServicePackage || forDaoPackage")
    private void forAppFlow() {}

    @Before("forAppFlow()")
    private void before(JoinPoint joinPoint){
        //display method we are calling
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("==========> @Before: calling method: "+ method);

        //display the args to the method
        Object[] args = joinPoint.getArgs();
        for(Object arg: args) {
            myLogger.info("==========> argument: " + arg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult")
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

        // display method we are returning from
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("=====>> in @AfterReturning: from method: " + theMethod);

        // display data returned
        myLogger.info("=====>> result: " + theResult);

    }

}
