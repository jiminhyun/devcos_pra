package com.example.springtheory.temp_20260701;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jspecify.annotations.Nullable;

public class ExceptionLoggingAdvice implements MethodInterceptor {

    @Override
    public @Nullable Object invoke(MethodInvocation invocation) throws Throwable {

        // 2. 실행될 메서드 이름
        String methodName = invocation.getMethod().getName();
        try {
            return invocation.proceed(); //다음 TARGET 실행
        } catch (Throwable e) {
            System.out.println("[ERROR] "+methodName+"에서 "+e.getMessage()+"오류 남!");
        }
        return null;
    }
}