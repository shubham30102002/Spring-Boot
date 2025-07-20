package com.example.springAOP.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectExpressions {
    @Pointcut("execution (* com.example.springAOP.dao.*.*(..))")
    public void forDaoPackage() {}

    @Pointcut("execution (* com.example.springAOP.dao.*.get*(..))")
    public void getter() {}

    @Pointcut("execution (* com.example.springAOP.dao.*.set*(..))")
    public void setter() {}

    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter() {}

}
