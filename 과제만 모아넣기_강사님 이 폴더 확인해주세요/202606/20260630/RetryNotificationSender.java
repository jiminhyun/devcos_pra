package com.example.springtheory.temp_20260630;

public class RetryNotificationSender implements NotificationSender {
    //데코레이터는 같은 인터페이스를 구현하는 클래스를 객체로 삼아서 메서드를 위임해서 처리하고 싶을때 쓴다.
    private final NotificationSender delegate;

    public RetryNotificationSender(NotificationSender delegate) {
        this.delegate = delegate;
    }

    @Override
    public void send(String to, String message) {
        int attempt = 0;
        while(true) {
            try {
                delegate.send(to, message);
                return;
            } catch (RuntimeException e) {
                attempt++;
                if(attempt >= 3) throw e;
            }
        }
    }
}