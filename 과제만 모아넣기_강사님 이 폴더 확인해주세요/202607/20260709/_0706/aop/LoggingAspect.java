package com.example.assignment._0706.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.example.assignment._0706.controller..*(..))")
    public void controllerLayer() {}

    @Around("controllerLayer()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs(); // Object[]로 받습니다.
        String argsString = (args != null) ? Arrays.toString(args) : "null"; // null 체크
        String className = joinPoint.getTarget().getClass().getSimpleName();
        long startTime = System.currentTimeMillis();
        System.out.println("Starting : " + className + "." + methodName + "(" + argsString + ")");
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("error : " + className + "." + methodName + "(" + argsString + ") : "+ endTime + "ms");
            throw e;
        } finally {
            long endTime = System.currentTimeMillis() - startTime;
            System.out.println("Ending : " + className + "." + methodName + "(" + argsString + ") : "+ endTime + "ms");
        }

    }
}
