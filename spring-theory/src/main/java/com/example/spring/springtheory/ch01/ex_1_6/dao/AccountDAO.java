package com.example.spring.springtheory.ch01.ex_1_6.dao;

public class AccountDAO {
    private SimpleConnectionMaker simpleConnectionMaker;

    public AccountDAO(SimpleConnectionMaker simpleConnectionMaker) {
        this.simpleConnectionMaker = simpleConnectionMaker;
    }
}
