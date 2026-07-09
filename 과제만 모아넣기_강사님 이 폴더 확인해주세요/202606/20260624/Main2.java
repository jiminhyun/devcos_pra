package com.example.springtheory.temp2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main2 {
    static void main(String[] args) {
        List<Product> products = new ArrayList<>(Arrays.asList(
                new Product("연필", 500), new Product("공책", 1200), new Product("지우개", 300),
                new Product("필통", 3000), new Product("볼펜", 800)));

        System.out.println("===== 1. 스트림 만들고 전체 출력 (forEach) =====");
        products.stream().forEach(p -> System.out.println(p.getName()+ " ("+p.getPrice()+"원)"));
        System.out.println("\n" +
                "===== 2. filter: 1000원 이상만 =====");
        products.stream().filter(p->p.getPrice() >= 1000).forEach(p -> System.out.println(p.getName()+ " ("+p.getPrice()+"원)"));
        System.out.println("\n" +
                "===== 3. map: 이름만 뽑기 =====");
        products.stream().map(Product::getName).forEach(System.out::println); //map으로 안 바꾸면 스트림 객체로 오기때문에 주소값이 나옴
        System.out.println("\n" +
                "===== 4. map vs flatMap (주문 속 상품 목록) =====");
        List<Order> orders = new ArrayList<>(Arrays.asList(new Order(1, Arrays.asList("연필", "공책")), new Order(2, Arrays.asList("필통", "볼펜", "공책"))));
        List<List<String>> byMap = orders.stream().map(Order::getItems).toList();
        List<String> byFlatMap = orders.stream().flatMap(o->o.getItems().stream()).toList(); // flatmap은 다중 list를 1차원 list로 만들고 stream을 써서 또 조건을 만들어서 필터링
        System.out.println("map     : "+byMap);
        System.out.println("flatMap : "+byFlatMap);
        System.out.println("\n" +
                "===== 5. filter + map + collect: 1000원 이상 상품 이름 리스트 =====");
        List<String> productName = products.stream().filter(p->p.getPrice() >=1000).map(Product::getName).collect(Collectors.toList());
        System.out.println(productName);
        System.out.println("\n" +
                "===== 6. 통계 =====");
        int count = (int) products.stream().filter(p->p.getPrice() >=1000).count();
        int sum = products.stream().mapToInt(Product::getPrice).sum();
        double average = products.stream().mapToInt(Product::getPrice).average().getAsDouble();
        List<String> sortedList = products.stream().sorted((Comparator.comparingInt(Product::getPrice))).map(Product::getName).collect(Collectors.toList());
        System.out.println("1000원 이상 개수: " +count);
        System.out.println("전체 가격 합계: "+sum);
        System.out.println("전체 가격 평균: "+average);
        System.out.println("가격 오름차순: "+sortedList);
    }
}
