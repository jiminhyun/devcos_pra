package com.example.springtheory.temp;

class TicketMachine {
    //eager
    private static int num = 0;
    private static final TicketMachine instance = new TicketMachine();  // 유일한 객체를 static으로 보관
    private TicketMachine() {}                                          // 생성자 private → 밖에서 new 금지
    static TicketMachine getInstance() { return instance; }             // 유일한 출입구
    int issue() {
        num++;
        return num;
    }
}