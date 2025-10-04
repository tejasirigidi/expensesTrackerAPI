package com.example.expenseTrackerApi.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitoring {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceMonitoring.class);

    @Around("execution(* com.example.expenseTrackerApi.service.UserService.findUser(..))")
    public Object monitorTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        Object obj = joinPoint.proceed();
        long end = System.currentTimeMillis();

        LOGGER.info(end-start+" ms");
        return obj;
    }
}
