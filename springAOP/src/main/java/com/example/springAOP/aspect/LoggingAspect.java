package com.example.springAOP.aspect;

import com.example.springAOP.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(1)
//@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class LoggingAspect {

    //@Before("execution(public void com.example.springAOP.dao.AccountDaoImpl.addAccount())")
    //@Before("execution(* com.example.springAOP.dao.*.*(..))")  --> all class and method in that package


//    @Before("execution(public void add*())")
//    public void beforeAddAccountAdvice(){
//        System.out.println("\n==============> executing @before advice method");
//    }


    @Before("com.example.springAOP.aspect.AspectExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAdvices(JoinPoint joinPoint){
        System.out.println("\n==============> executing @before using @pointcut expression");

        //display method signature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method signature is : "+ methodSignature);

        //display method arguments
        Object[] args = joinPoint.getArgs();
        for(Object tempArg : args){
            System.out.println("Method arguments are: " + tempArg);
            if (tempArg instanceof Account) {

                // downcast and print Account specific stuff
                Account theAccount = (Account) tempArg;

                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }
    }


    @AfterReturning(
            pointcut = "execution(* com.example.springAOP.dao.AccountDao.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        System.out.println("\n=====>>> result is: " + result);

        // let's post-process the data ... let's modify it :-)

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);


        // print out the results of the method call
        System.out.println("\n=====>>> result is: " + result);

    }

    @AfterThrowing(
            pointcut = "execution(* com.example.springAOP.dao.AccountDao.findAccounts(..))",
            throwing = "exception"
    )
    public void afterThrowingFindAccountAdvice(JoinPoint joinPoint, Throwable exception){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        System.out.println("\n=====>>> The exception is: " + exception);
    }

    @After("execution(* com.example.springAOP.dao.AccountDao.findAccounts(..))")
    public void afterFinallyFindAccountAdvice(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: " + method);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop through accounts

        for (Account tempAccount : result) {

            // get uppercase version of name
            String theUpperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(theUpperName);
        }
    }


    @Around("execution(* com.example.springAOP.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method
        Object result = null;

        try {
            result = theProceedingJoinPoint.proceed();
        }
        catch (Exception exc) {
            // log the exception
            System.out.println(exc.getMessage());

            // give user a custom message
            result = "Major accident! But no worries, your private AOP helicopter is on the way!";
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n=====> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

}
