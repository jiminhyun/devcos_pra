package com.example.springtheory.temp;

public class Main2 {

    static void main(String[] args) {
        NaiveTicketMachine nta = new NaiveTicketMachine();
        NaiveTicketMachine ntb = new NaiveTicketMachine();
        System.out.println("===== 1. 싱글톤 없이: 번호표 두 대 (버그!) =====");
        System.out.println("A 기계가 발급: "+nta.issue()+"번");
        System.out.println("B 기계가 발급: "+ntb.issue()+"번  <- 중복!");
        System.out.println("A 기계가 발급: "+nta.issue()+"번");
        System.out.println("B 기계가 발급: "+ntb.issue()+"번  <- 또 중복!");
        System.out.println();
        //TicketMachine ticketMachine = new TicketMachine();
        System.out.println("===== 2. 싱글톤 적용: 번호표는 하나뿐 =====");
        TicketMachine w1 = TicketMachine.getInstance();
        TicketMachine w2 = TicketMachine.getInstance();
        TicketMachine w3 = TicketMachine.getInstance();
        System.out.println("1번 창구가 발급: "+w1.issue()+"번");
        System.out.println("2번 창구가 발급: "+w2.issue()+"번");
        System.out.println("1번 창구가 발급: "+w1.issue()+"번");
        System.out.println("3번 창구가 발급: "+w3.issue()+"번");
        System.out.println("같은 기계인가? "+(w1 == w2));
        System.out.println();
        System.out.println("===== 3. lazy 초기화 (설정 관리자) =====");
        Settings s1 = Settings.getInstance();
        Settings s2 = Settings.getInstance();
        s1.setTheme("blue");
        s2.setTheme("dark");
        System.out.println("앱 설정 - 테마: "+s1.getTheme());
        System.out.println("앱 설정 - 테마: "+s2.getTheme()+" (어디서 불러도 같은 설정)");
        System.out.println("같은 설정 객체인가? "+(s1 == s2));
        //★ enum으로 싱글톤을 만드는 방법(enum Settings { INSTANCE; … })을 조사해보세요. 자바에서 가장 안전한 싱글톤으로 꼽혀요.
        // -> enum은 기본 final static 변수기 때문에 관리하기 편하다. 싱클턴은 하나기 때문에 INSTANCE 하나만 할당해주고 남은 값은 그대로 작성해주면 된다.
    //싱글톤의 단점(테스트하기 어렵다, 전역 상태가 늘어난다)을 한 문단으로 정리해보세요. 지난 시간 배운 IoC/DI가 이 단점을 어떻게 완화하는지도 함께요.
        //싱글톤은 첫 클래스 구현이나 인스턴스 메서드 호출시 고정되는 것이 있기 때문에 테스트 하기 힘들고 공유 객체라서 static을 쓰기 때문에 전역 상태가 늘어난다.
        //ioc는 단순 클래스의 제어권을 넘겨 싱글톤 빈으로 만들어주기 때문에 di의 기법을 적용하여 테스트하기 쉽게 만들어주고 이를 통해서 전역 상태가 늘어나는 단점을 해결해준다.
    }
}
