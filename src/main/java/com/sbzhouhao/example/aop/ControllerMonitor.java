package com.sbzhouhao.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerMonitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerMonitor.class);

    @AfterReturning("execution(* com.sbzhouhao.example.controller.*Controller.*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        LOGGER.info("Completed: " + joinPoint);
    }
}
