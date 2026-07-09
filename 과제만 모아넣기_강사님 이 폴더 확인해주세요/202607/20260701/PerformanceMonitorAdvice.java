package com.example.springtheory.temp_20260701;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jspecify.annotations.Nullable;

public class PerformanceMonitorAdvice implements MethodInterceptor {

    @Override
    public @Nullable Object invoke(MethodInvocation invocation) throws Throwable {
        // 시작 시간 기록
        // 1. 타겟 클래스 이름
        //String className = invocation.getClass().getName();  //프레임워크가 실행하는 객체의 이름이라서 리플렉션을 가지고 옴
        String className = invocation.getMethod().getDeclaringClass().getSimpleName();

        // 2. 실행될 메서드 이름
        String methodName = invocation.getMethod().getName();
        long startTime = System.currentTimeMillis();
        try {
            return invocation.proceed(); //다음 TARGET 실행
        } finally {
            // 종료 시간 기록
            long endTime = System.currentTimeMillis();
            // 경과 시간 계산
            long executionTime = endTime - startTime;
            System.out.println("[PERF] " + className+ "." + methodName + " : " + executionTime + "ms");
        }
    }
}
