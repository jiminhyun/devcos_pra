package com.example.springtheory.temp_20260701;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {

    @Bean //조언자 등록
    public Advisor performanceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.example.springtheory.temp_20260701..*Service.get*(..))");
        return new DefaultPointcutAdvisor(pointcut, new PerformanceMonitorAdvice());
    }

    @Bean //조언자 등록
    public Advisor performanceAdvisor2() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.example.springtheory.temp_20260701..*Service.get*(..))");
        return new DefaultPointcutAdvisor(pointcut, new ExceptionLoggingAdvice());
    }

    @Bean //후처리기
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }


    @Bean //서비스 빈들 만들어지고 나서 후처리기로 처리한다.
    public OrderService orderService() {
        return new OrderServiceImpl();
    }

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl();
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }
}
