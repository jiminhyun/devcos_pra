package com.example.springtheory.temp_20260630;

public class TimingNotificationSender implements NotificationSender {
    //데코레이터는 같은 인터페이스를 구현하는 클래스를 객체로 삼아서 메서드를 위임해서 처리하고 싶을때 쓴다.
    private final NotificationSender delegate;

    public TimingNotificationSender(NotificationSender delegate) {
        this.delegate = delegate;
    }

    @Override
    public void send(String to, String message) {
        long startTime = System.currentTimeMillis();
        delegate.send(to, message);
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("발송에 걸린 시간: "+executionTime);
    }
}