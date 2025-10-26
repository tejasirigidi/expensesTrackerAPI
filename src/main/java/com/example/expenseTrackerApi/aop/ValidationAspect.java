package com.example.expenseTrackerApi.aop;

import com.example.expenseTrackerApi.model.entity.Users;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.example.expenseTrackerApi.service.UserService.findUser(..)) && args(user)")
    public Object validate(ProceedingJoinPoint joinPoint, Users user) throws Throwable {

        if(user.getUsername()==null){
            LOGGER.info("Username is null, updating it");
            user.setUsername("Venkata");
        }

        Object obj = joinPoint.proceed(new  Object[]{user});

        return obj;

    }


}
