package com.cplh.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class TimeAspect {

    /**
     * 在哪些方法起作用，在什么时候起作用
     */
    @Around("execution (* com.cplh.web.rest.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is " + arg);
        }
        long start = new Date().getTime();
        Object proceed = pjp.proceed();
        System.out.println("time aspect 耗时： " + (new Date().getTime() - start));
        System.out.println("time aspect end");
        return proceed;
    }

}
