package com.example.springAOP.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class HeaderValidationAspect {
    @Before("com.example.springAOP.aspect.AspectExpressions.forDaoPackageNoGetterSetter()")
    public void headerValidation(){
        System.out.println("\n==============> Performing header validation using AOP");
    }
}
