package com.example.springtheory.temp_20260630;

public class Main {

    static void main(String[] args) {
        /*NotificationService nsEmail = new NotificationService(new EmailNotificationSender());
        NotificationService nsSms = new NotificationService(new SmsNotificationSender());
        NotificationService nsKakao = new NotificationService(new KakaoNotificationSender());
        NotificationService nsFlaky = new NotificationService(new FlakyEmailSender());
        LoggingNotificationSender loggingNotificationSender = new LoggingNotificationSender(new FlakyEmailSender());*/

        /*NotificationSender sender =
                new TimingNotificationSender(       // ③ 가장 바깥: 전체 소요 시간 측정
                        new LoggingNotificationSender(  // ② 로그 남기고
                                new RetryNotificationSender(// ① 실패하면 재시도하며
                                        new FlakyEmailSender())));// (실제 발송 대상)*/

        NotificationSender sender =
                new TimingNotificationSender(       // ③ 가장 바깥: 전체 소요 시간 측정
                        new RetryNotificationSender(  // ② 로그 남기고
                                new LoggingNotificationSender(// ① 실패하면 재시도하며
                                        new FlakyEmailSender())));// (실제 발송 대상)

        new NotificationService(sender).notifyUser("user@test.com", "안녕하세요");

        //a-3 -> 매개변수의 타입을 인터페이스로 하고 있기 때문에 그를 구현하는 클래스들(추상화)을 쓸 경우 코드 변경없이 쓸 수 있다.
        //c-2 -> 재시도가 앞으로 왔고 로그 출력이 뒤로 갔기 때문에 재시도 3번을하면서 발송시작을 3번 불러서 로그가 3번찍힌다. , 단 발송완료는 flaky가 무사 호출이 되야 호출이 되므로 1번만 찍힌다.
        //추상화"가 푼 문제와 "데코레이터"가 푼 문제가 각각 무엇이었는지 한 문단으로 구분해서 설명
        //-> 추상화는 클래스들을 인터페이스에 의존하게 하여 클라이언트가 코드를 고칠 필요가 없게 결합도를 낮췄고
        //데코레이터는 기능들을 위임, 재정의(조합)하여 기능을 여러개 만들어 클래스들을 중첩해야하는 클래스 폭발 문제를 개선 및 객체에 새로운 책임을 주는 구조적 어려움을 데코레이터에서는 이를 해결할 수 있게 한다.
        /*
        D-1. 속도 제한 데코레이터: 1초에 1건만 통과시키고, 초과 호출은 잠시 대기시키는 RateLimitNotificationSender를 추가하라.
D-2. 조건부 발송: 메시지에 특정 금칙어가 있으면 위임하지 않고 차단하는 데코레이터를 추가하라. (데코레이터가 위임을 안 할 수도 있다는 걸 보여줌)
D-3. 스프링 DI 조립: 위 조합을 @Configuration + @Bean으로 옮겨, 데코레이터 체인을 설정에서 구성해보라.
1.->현재시간과 호출시간을 분리하여 그를 이용한 thread.sleep을 활용
2.->단순함 걍 message부분을 if 유효성검사를 해서 return을 때리면됨
3.->NotificationSender sender =
                new TimingNotificationSender(       // ③ 가장 바깥: 전체 소요 시간 측정
                        new RetryNotificationSender(  // ② 로그 남기고
                                new LoggingNotificationSender(// ① 실패하면 재시도하며
                                        new FlakyEmailSender())));// (실제 발송 대상)
    이부분을 daofactory마냥 빈으로 등록해서 불필요하게 매번 쓰기 싫을때 설정을 구성한다는 것이다.
         */
    }
}
