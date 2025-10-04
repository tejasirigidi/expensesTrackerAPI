package com.example.expenseTrackerApi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;




@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    // return type, class-name.method-name(args)

    @Before("execution(* com.example.expenseTrackerApi.service.UserService.findUser(..)) || execution(* com.example.expenseTrackerApi.service.UserService.findUser(..))")
    public void logMethodcall(JoinPoint joinPoint) {
        System.out.println("method call " + joinPoint.getSignature().getName());
    }

    @After("execution(* com.example.expenseTrackerApi.service.UserService.findUser(..)) || execution(* com.example.expenseTrackerApi.service.UserService.findUser(..))")
    public void logMethod(JoinPoint jp) {
        System.out.println("method executed" + jp.getSignature().getName());
    }


    @AfterReturning("execution(* com.example.expenseTrackerApi.service.UserService.findUser(..)) || execution(* com.example.expenseTrackerApi.service.UserService.findUser(..))")
    public void logMethodReturn(JoinPoint jp) {
        System.out.println("method executed succesfully" + jp.getSignature().getName());
    }


    @AfterThrowing("execution(* com.example.expenseTrackerApi.service.UserService.findUser(..)) || execution(* com.example.expenseTrackerApi.service.UserService.findUser(..))")
    public void logMethodError(JoinPoint jp) {
        System.out.println("method has some issues" + jp.getSignature().getName());
    }

}
