package com.example.springtheory.temp_20260701;

import org.jspecify.annotations.Nullable;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.lang.reflect.Method;

public class MyCustomPointcut implements Pointcut {
    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return method.getName().startsWith("save");
            }

            @Override
            public boolean isRuntime() {
                return false;
            }

            @Override
            public boolean matches(Method method, Class<?> targetClass, @Nullable Object... args) {
                return false;
            }
        };
    }

    @Override
    public ClassFilter getClassFilter() {
        return new ClassFilter() {
            @Override
            public boolean matches(Class<?> clazz) {
                return clazz.getSimpleName().endsWith("ServiceImpl");
            }
        };
    }
}
