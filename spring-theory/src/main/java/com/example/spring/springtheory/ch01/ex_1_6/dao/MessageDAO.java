package com.example.spring.springtheory.ch01.ex_1_6.dao;

public class MessageDAO {
    private SimpleConnectionMaker simpleConnectionMaker;

    public MessageDAO(SimpleConnectionMaker simpleConnectionMaker) {
        this.simpleConnectionMaker = simpleConnectionMaker;
    }
}
