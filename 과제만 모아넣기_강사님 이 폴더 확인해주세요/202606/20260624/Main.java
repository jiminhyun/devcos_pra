package com.example.springtheory.temp2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

public class Main {
    static void main(String[] args) throws InterruptedException {
        //함수형 인터페이스 추상 메서드 하나 가진 인터페이스
        Operation add = new Operation() {
            @Override
            public int apply(int a, int b) {
                return a+b;
            }
        };
        System.out.println("===== 1. 익명 클래스 vs 람다 (같은 동작) =====");
        System.out.println(add.apply(3,4));
        Operation add_2 = (a, b) -> a + b; //람다 step2
        System.out.println(add_2.apply(3,4));

        Operation add1 = (int a , int b) -> {return a+b;};
        Operation add2 = (a , b) -> {return a+b;};
        Operation add3 = (a, b) -> a + b;
        Operation sub = (a, b) -> a - b;
        Operation mul = (a, b) -> a * b;
        System.out.println("===== 2. 람다로 만든 연산들 =====");
        System.out.println(add3.apply(3,4));
        System.out.println(sub.apply(9,2));
        System.out.println(mul.apply(3,5));
        System.out.println("===== 3. 매개변수 개수별 람다 =====");
        Runnable ra = () -> System.out.println("hello");
        Thread t1 = new Thread(ra);
        t1.start();
        t1.join();
        Printer p = System.out::println;
        p.print("hello");
        System.out.println(add3.apply(10, 20));
        System.out.println("===== 4. 실전: Comparator로 길이순 정렬 =====");
        ArrayList<String> list = new ArrayList<>(Arrays.asList("가나다", "가", "라마"));
        for(String i : list) System.out.println(i);
        System.out.println("==================");
        list.sort(Comparator.comparingInt(String::length));
        for(String i : list) System.out.println(i);
        /*도전과제
        1. -> ArithmeticException 발생
        2. -> Comparator 리턴 값이 음수면 오름차순 양수면 내림차순 같으면 그대로
        3. -> 정렬 기준을 "글자 수"가 아니라 "가나다 사전순"으로 바꿔보세요. (s1.compareTo(s2)) 람다식 그대로
        4. -> Predicate<String> test = (String::isEmpty);
        5. -> list.sort(Comparator.comparingInt(String::length));, 메서드 참조는
        람다의 파라미터가 그대로 해당 메서드의 인자로 들어갈 때:(a, b) -> Math.max(a, b) -> Math::max
        람다의 파라미터가 호출되는 메서드의 '대상 객체'가 될 때:str -> str.toUpperCase() -> String::toUpperCase

        클래스명::메서드명 (System::println)
객체참조::메서드명 (myObject::myMethod)
클래스명::new (생성자 참조)
         */

// 이해하는데 2~3시간 걸린 것
        // list.sort(Comparator.comparingInt(String::length)); == list.sort((c1, c2) -> Integer.compare(c1.length(), c2.length()));
        // 둘이 같다 함수의 순차실행을 생각하면 안된다, 껍데기를 벗겨서 compare 부분의 String.length 2개의 인자를 넘겨서 수행해야 한다고 봐야 한다. 바텀업을 생각하자.

    }
}
