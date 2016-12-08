package com.hawk.commentcenter.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author junxiong.lang
 * @date 2016/11/29 10:17
 */
@Component
@Aspect
public class LogAop {
    @Around("execution(* com.hawk.commentcenter.service.impl.GreetServiceImpl.*(..))")
    public void doAround(ProceedingJoinPoint pjp) throws Throwable{
        String methodName = pjp.getSignature().getName();

        System.out.println(methodName + " do before.");
        pjp.proceed();
        System.out.println(methodName + " do after.");
    }
}
