package com.example.springtheory.temp;

import ch.qos.logback.core.joran.conditional.ThenAction;

public class Main3 {
    static final int maxNum = 30;
    static int badNum  = 0;
    static int goodNum  = 0;
    static void main(String[] args) throws InterruptedException {
        //도전과제 3: 공유해야하는 상태 -> 테이블 연결 같은 굳이 또 해야할 필요가 없는 것, 요청별 상태-> 테이블에서 조건에 맞는 행 카운트 같은 것


        /* 도전과제 4 관련
        class SmartContainer {
    private final Map<String, Object> singletonRegistry = new HashMap<>();

    // <T>를 써서 꺼낼 때 원하는 타입으로 자동 변환되게 만듦
    public <T> T getBean(String name, Class<T> requiredType) { 제네릭 메서드
        if (!singletonRegistry.containsKey(name)) {
            if (name.equals("coffeeMaker")) {
                singletonRegistry.put(name, new CoffeeMaker());
            }
        }
        // 원래 Object인 것을 requiredType(예: CoffeeMaker.class)으로 캐스팅해서 반환
        return requiredType.cast(singletonRegistry.get(name));
    }
}

// 사용할 때: 형변환이 필요 없음!
CoffeeMaker maker = SmartContainer.getBean("coffeeMaker", CoffeeMaker.class);
         */

        //★ 무상태가 깨지기 쉬운 다른 예(예: SimpleDateFormat을 필드로 공유)를 조사해보세요. 실무에서 자주 터지는 버그예요.
        //-> 캘린더 객체를 가지고 오면서 set하는 부분에서 필드 참조를 하기 때문에 무상태가 깨진다.
        System.out.println("===== 같은 싱글톤을 30개 스레드가 동시에 사용 =====");
        Thread[] t1 = new Thread[maxNum];
        for (int i = 0; i < maxNum; i++) {
            String name = "bad" + String.valueOf(i);
            t1[i] = new Thread(()->{
                if(!name.equals(GreetingServiceBad.getInstance().greet(name))) {
                    synchronized (Main3.class) { badNum++; } //동기화 안 걸면 꼬임 도전과제 2랑 연결되는 부분
                }
            });
        }
        for (Thread t: t1) {
            t.start();
        }
        for (Thread t: t1) {
            t.join();
        }
        Thread[] t2 = new Thread[maxNum];
        for (int i = 0; i < maxNum; i++) {
            String name = "good" + String.valueOf(i);
            t2[i] = new Thread(()->{
                if(!name.equals(GreetingServiceGood.getInstance().greet(name))) {
                    synchronized (Main3.class) { goodNum++; }
                }
            });
        }
        for (Thread t: t2) t.start();
        for (Thread t: t2) {
            t.join();
        }
        System.out.println("[필드에 저장] 데이터 엉킴: "+badNum+"건 / "+maxNum+"건");
        System.out.println("[파라미터로]  데이터 엉킴: "+goodNum+"건 / "+maxNum+"건");
        System.out.println();
        System.out.println("===== 필드에 둬도 되는 것: 다른 싱글톤 참조 =====");
        UserDAO userDAO1 = UserDAO.getInstance();
        UserDAO userDAO2 = UserDAO.getInstance();
        System.out.println(userDAO1.findUser("kim"));
        System.out.println(userDAO2.findUser("lee"));
        System.out.println("같은 DAO인가? " + (userDAO1 == userDAO2));
    }
}
