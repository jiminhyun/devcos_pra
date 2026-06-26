package com.example.springtheory.temp_20260626.dao;

import com.example.springtheory.temp_20260626.domain.User;

public class Main {
    static void main(String[] args) {
        Database db = new Database();
        UserDao userDao = new UserDao(db);
        System.out.println("== (별도 클래스) deleteAll ==");
        userDao.deleteAll_1();
        System.out.println("\n" +
                "== (익명 클래스) add(김) ==");
        User user1 = new User();
        user1.setId("1"); user1.setName("김"); user1.setPassword("1");
        userDao.add_2(user1);
        System.out.println("\n" +
                "== (람다) add(이) ==");
        User user2 = new User();
        user2.setId("2"); user2.setName("이"); user2.setPassword("12");
        userDao.add_3(user2);
        System.out.println("\n" +
                "현재 사용자 수: "+db.getUsers().size());
        for(User user : db.getUsers()) {
            System.out.println("사용자: "+user.getName());
        }
        System.out.println(userDao.get("2").getName());
        //도전 과제 2 deleteAll처럼 캡처할 값이 없는 전략은 별도 클래스/익명/람다 중 무엇이 가장 깔끔한지 비교해보세요.
        //->밑에 설명에 있듯이 람다로 하면 됨
        //도전과제 4 ex_3_3참고

        //전략이 매우 복잡하고 재사용될 가능성이 높다: 별도 클래스로 분리
        //전략이 단순하고, 특정 메서드에서만 딱 한 번 쓰인다: -> 람다(익명은 굳이 x)
    }
}
