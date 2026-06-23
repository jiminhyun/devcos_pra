package com.example.springtheory.temp;

public class NaiveTicketMachine {
    private int num = 0;
    int issue() {
        num++;
        return num;
    }
}
