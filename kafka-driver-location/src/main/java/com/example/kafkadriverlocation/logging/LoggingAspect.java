package com.example.kafkadriverlocation.logging;

import com.example.kafkadriverlocation.utility.Utility;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.SQLException;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("within(com.example.kafkadriverlocation.service..*)")
    public void printLog(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            log.info("Invoked... {}::{}() and args={}", className, methodName, Utility.objectToJsonString(args));
        } else {
            log.info("Invoked... {}::{}()", className, methodName);
        }
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long timeMillis = System.currentTimeMillis() - start;

        log.info("Completed... {}::{}() and result={} and {}ms time taken", className, methodName, Utility.objectToJsonString(result), timeMillis);
    }

    @AfterThrowing(pointcut = "execution(* com.example..*.*(..))", throwing = "ex")
    public void handleExceptions(Exception ex, ProceedingJoinPoint joinPoint) {
        logErrorBasedOnExceptionType(ex, joinPoint);
    }

    private void logErrorBasedOnExceptionType(Exception ex, ProceedingJoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Object[] args = joinPoint.getArgs();
        if (ex instanceof IOException ioEx) {
            log.error("Exception... {}::{}() and args={} IOException occurred: {}", className, methodName, Utility.objectToJsonString(args), ioEx.getMessage());
        } else if (ex instanceof SQLException sqlEx) {
            log.error("Exception... {}::{}() and args={} SQLException occurred: {}", className, methodName, Utility.objectToJsonString(args), sqlEx.getMessage());
        } else if (ex instanceof NullPointerException nullPointerEx) {
            log.error("Exception... {}::{}() and args={} NullPointerException occurred: {}", className, methodName, Utility.objectToJsonString(args), nullPointerEx.getMessage());
        } else {
            log.error("Exception... {}::{}() and args={} Exception occurred: {}", className, methodName, Utility.objectToJsonString(args), ex.getMessage());
        }
    }
}
