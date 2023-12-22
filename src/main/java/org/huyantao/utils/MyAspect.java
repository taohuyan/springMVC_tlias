package org.huyantao.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author:呼延涛
 * @version:1.0
 */
@Component
@Aspect
@Slf4j
public class MyAspect {

    @Pointcut("execution(* org.huyantao.Service.Impl.DeptServiceImpl.*(..))")
    private void pt(){}

    @Before("pt()")
    public void before(){
        log.info("before");
    }
    @Around("pt()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("Around before");

        Object proceed = proceedingJoinPoint.proceed();

        log.info("Around after");
        return proceed;
    }
    @After("pt()")
    public void after(){
        log.info("After");
    }
    @AfterReturning("pt()")
    public void Return(){
        log.info("afterReturning");
    }
    @AfterThrowing("pt()")
    public void Throw(){
        log.info("afterThrowing");
    }
}
