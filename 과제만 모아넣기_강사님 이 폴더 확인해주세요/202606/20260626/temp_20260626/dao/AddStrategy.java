package com.example.springtheory.temp_20260626.dao;

import com.example.springtheory.temp_20260626.domain.User;

public class AddStrategy implements StatementStrategy {
    final User user; // 다른 메서드에서 예기치 못하게 변경(side effect)할 수 있으니 final 선언
    public AddStrategy(User user) {
        this.user = user;
    }

    @Override
    public void run(Database db) {
        db.getUsers().add(user);
        System.out.println("  [전략-익명] 추가: "+user.getName());
    }
}
