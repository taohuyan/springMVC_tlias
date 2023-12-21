package org.huyantao.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author:呼延涛
 * @version:1.0
 */
@Component
@Aspect
@Slf4j
public class TimeAspect {

    @Around("execution(* org.huyantao.Service.*.*(..))")//切入点表达式
    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        log.info(proceedingJoinPoint.getSignature()+"执行耗时：{}ms", end-begin);
        return result;
    }
}
