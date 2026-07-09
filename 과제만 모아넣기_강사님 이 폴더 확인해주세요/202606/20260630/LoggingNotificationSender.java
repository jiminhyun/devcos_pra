package com.example.springtheory.temp_20260630;

public class LoggingNotificationSender implements NotificationSender {
    //데코레이터는 같은 인터페이스를 구현하는 클래스를 객체로 삼아서 메서드를 위임해서 처리하고 싶을때 쓴다.
    private final NotificationSender delegate;

    public LoggingNotificationSender(NotificationSender delegate) {
        this.delegate = delegate;
    }

    @Override
    public void send(String to, String message) {
        // TODO 1: "발송 시작" 로그 (to 포함)
        System.out.printf("[Log] to=%s : %s%n", to, "| 발송 시작");
        // TODO 2: delegate.send(to, message);   ← 실제 발송은 위임
        delegate.send(to, message);
        // TODO 3: "발송 완료" 로그
        System.out.printf("[Log] to=%s : %s%n", to, "| 발송 완료");
    }
}