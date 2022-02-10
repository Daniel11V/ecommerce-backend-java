package com.coderhouse.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Aspect
@Component
public class AspectAfterMethods {

    @Pointcut("execution(* com.coderhouse.controller.*.*(..))")
    void afterAnyRequest() {}

    @Around("afterAnyRequest()")
    Object aroundAdviceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        Object ret = joinPoint.proceed();
        long end = System.nanoTime();

        log.info("Se ejecutó el método {}.{}() con una duración de {} ms",
                joinPoint.getSignature().getDeclaringTypeName()
                        .replace("com.coderhouse.controller.", ""),
                joinPoint.getSignature().getName(),
                TimeUnit.NANOSECONDS.toMillis(end - start));
        return ret;
    }
}
