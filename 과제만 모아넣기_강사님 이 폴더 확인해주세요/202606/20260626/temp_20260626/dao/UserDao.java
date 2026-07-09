package com.example.springtheory.temp_20260626.dao;

import com.example.springtheory.temp_20260626.domain.User;

public class UserDao {
    private Database db;

    public UserDao(Database db) {
        this.db = db;
    }

    void context(StatementStrategy strategy) {
        db.open(); //디비를 열고
        strategy.run(db); //변하는 전략을 db에서 실행하고
        db.close(); //db를 닫는다.
    }

    void deleteAll_1() {
        context(new DeleteAllStrategy());
    }

    void deleteAll_2() {
        StatementStrategy strategy = new StatementStrategy() {
            @Override
            public void run(Database db) {
                db.getUsers().clear();
                System.out.println("  [전략-별도클래스] 전체 삭제");
            }
        };
        context(strategy);
    }

    void deleteAll_3() {
        context(db->{
            db.getUsers().clear();
            System.out.println("  [전략-별도클래스] 전체 삭제");
        });
        //context(db-> db.getUsers().clear()); 도전과제 3
    }

    void add_1(User user) {
        context(new AddStrategy(user));
    }

    void add_2(User user) {
        StatementStrategy strategy = new StatementStrategy() {
            @Override
            public void run(Database db) {
                db.getUsers().add(user);
                System.out.println("  [전략-익명] 추가: "+user.getName());
            }
        };
        context(strategy);
    }

    void add_3(User user) {
        context(db->{
            db.getUsers().add(user);
            System.out.println("  [전략-익명] 추가: "+user.getName());
        });
    }

    User get(String id) { //도전과제 1
        final User[] temp = new User[1]; //배열은 바뀌지는 않는다는 꼼수를 이용하여 람다가 외부 변수를 참조할 수 있게 함
        context(db-> {
            for(User user: db.getUsers()) {
                if (user.getId().equals(id)) temp[0] = user;
            }
        });
        return temp[0];
    }
}
