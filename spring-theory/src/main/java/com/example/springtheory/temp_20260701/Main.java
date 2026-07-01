package com.example.springtheory.temp_20260701;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AopConfig.class);
        OrderService orderService = ctx.getBean("orderService", OrderService.class); //조건이 많을 경우 매개변수를 더 쓰는 것이다.
        MemberService memberService = ctx.getBean("memberService", MemberService.class); //조건이 많을 경우 매개변수를 더 쓰는 것이다.
        ProductService productService = ctx.getBean("productService", ProductService.class); //조건이 많을 경우 매개변수를 더 쓰는 것이다.
        System.out.println("===== 주문 서비스 호출 =====");
        System.out.println(orderService.placeOrder("기계식 키보드"));
        System.out.println("\n" +
                "===== 회원 서비스 호출 =====");
        System.out.println(memberService.register("kim"));
        System.out.println("\n" +
                "===== 상품 서비스 호출 (Step 6: 설정 무수정으로 자동 적용) =====");
        System.out.println(productService.getProduct("A-100"));
        System.out.println("\n" +
                "===== 진짜 프록시인지 확인 =====");
        System.out.println("orderService 의 실제 타입: "+orderService.getClass() +"   ← OrderServiceImpl 이 아니다!");
        /*
        ★ 연결 정리: 지난 시간에 손으로 짠 데코레이터(예: RetryNotificationSender)와 이번 자동 프록시를 비교해, "무엇이 자동화되었는가"를 한 문단으로 적어보세요.

         이것은 리플렉션을 이용한 부분이라서 데코레이터에서 구현 객체를 받아오는 부분이 자동화 된 것이다.
         */
    }
}
