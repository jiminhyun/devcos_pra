package com.example.springtheory.temp;

public class Dao {
}

class UserDAO {
    private static final UserDAO instance = new UserDAO();

    private UserDAO() {
    }

    static UserDAO getInstance() {
        return instance;
    }
    private ConnectionMaker cn = SimpleConnectionMaker.getInstance();

    String findUser(String userid) {
        return userid + " 조회 " + cn.makeConnection();
    }
}

interface ConnectionMaker {
    String makeConnection();
}

class SimpleConnectionMaker implements ConnectionMaker {
    private static final SimpleConnectionMaker instance = new SimpleConnectionMaker();

    private SimpleConnectionMaker() {
    }

    static SimpleConnectionMaker getInstance() {
        return instance;
    }
    @Override
    public String makeConnection() {
        return "[DB연결]";
    }
}