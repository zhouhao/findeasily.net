package net.findeasily.website.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerMonitor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerMonitor.class);

    @Pointcut("execution(* net.findeasily.website.controller.*Controller.*(..))")
    public void monitor() {
    }

    @Around("monitor()")
    public Object logServiceAccess(ProceedingJoinPoint pjp) throws Throwable {
        LOGGER.info("processing: " + pjp);
        long start = System.currentTimeMillis();
        LOGGER.info("Going to call the method.");
        Object output = pjp.proceed();
        LOGGER.info("Method execution completed.");
        long elapsedTime = System.currentTimeMillis() - start;
        LOGGER.info(pjp.getSignature() + " execution time: " + elapsedTime + " milliseconds.");
        return output;
    }
}
