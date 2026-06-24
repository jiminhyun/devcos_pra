package com.example.spring.springtheory.ch02.ex_2_1.dao;

public class AccountDAO {
    private SimpleConnectionMaker simpleConnectionMaker;

    public AccountDAO(SimpleConnectionMaker simpleConnectionMaker) {
        this.simpleConnectionMaker = simpleConnectionMaker;
    }
}
