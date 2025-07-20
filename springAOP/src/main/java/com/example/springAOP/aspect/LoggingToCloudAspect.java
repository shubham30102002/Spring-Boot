package com.example.springAOP.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class LoggingToCloudAspect {
    @Before("com.example.springAOP.aspect.AspectExpressions.forDaoPackageNoGetterSetter()")
    public void logToCloud(){
        System.out.println("\n==============> Logging to cloud using AOP");
    }
}
