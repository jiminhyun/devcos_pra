package temp2;

import java.util.Scanner;

class SleepThread extends Thread {
    public void run() {
        for (int i = 0; i < 300; i++) System.out.print("-");
        try {
            Thread.sleep(2000);     // 이 스레드를 2초 멈춤
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("<<종료>>");
    }
}

class Thread1 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("-");
        }
        System.out.println();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("종료");
    }
}

class Thread2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 300; i++) {
            System.out.print("|");
        }
        System.out.println();
    }
}
class Thread3 extends Thread {// interrupt
    @Override
    public void run() {
        int i = 10;
        while (i != 0 && !isInterrupted()) {
            System.out.println(i--);
            for (long x = 0; x < 2_500_000_000L; x++) ;  // 시간 지연(busy)
        }
        System.out.println("카운트가 종료되었습니다.");
    }
}

class Thread3_2 extends Thread {// interrupt
    @Override
    public void run() {
        int i = 10;
        while (i != 0 && !isInterrupted()) {
            System.out.println(i--);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("강제 종료");
                break;
            }
        }
        System.out.println("카운트가 종료되었습니다.");
    }
}

class Th4 extends Thread {// yield
    private String name;
    public Th4(String name) { this.name = name; }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " 실행 중. 반복: " + i);
            Thread.yield();   // 남은 실행시간 양보(힌트) //우선순위는 뜯어봐야 알기때문에 보장을 알 수가 없다.
            try { Thread.sleep(500); } catch (InterruptedException e) { break; }
        }
    }
}
class Th5 extends Thread {// yield
    private String name;
    public Th5(String name) { this.name = name; }
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " 실행 중. 반복: " + i);
            Thread.yield();   // 남은 실행시간 양보(힌트)
            try { Thread.sleep(500); } catch (InterruptedException e) { break; }
        }
    }
}


public class Assignment_1 {
    static void exam1() {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        thread2.start();
    }

    static void exam2() {
        SleepThread st = new SleepThread();
        st.start();
        /*try {
            st.sleep(2000); //== Thread.sleep(2000); //작동중인 스레드를 대상 static이라서 그럼
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
    }

    static void exam3() { // while 구문 수행중이라면 입력장치에서 입력 받을 때 while구문을 isInterrupted로 여부 체크하고 빠져나옴
        Thread3 thread3 = new Thread3();
        thread3.start();
        new Scanner(System.in).nextLine();
        thread3.interrupt();
    }

    static void exam4() {  // 이건 isInterrupted 필요없다. 중요한건 WAITING 상태에서 받은 interrupt를 수행한다는 것
        Thread3 thread3 = new Thread3();
        thread3.start();
        new Scanner(System.in).nextLine();
        thread3.interrupt();
    }

    static void exam5() { //우선순위는 뜯어봐야 알기때문에 보장을 알 수가 없다.
        Th4 th4 = new Th4("t1");
        Th5 th5 = new Th5("t2");
        th4.start();
        th5.start();
    }
    static void exam6() {
        Th4 th4 = new Th4("t1");
        Th5 th5 = new Th5("t2");
        th4.start();
        th5.start();
        long start = System.currentTimeMillis();
        try {
            th4.join();
            th5.join(); //이것을 실행한 메인스레드는 해당 스레드가 끝나야 다음을 수행 가능하다.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("소요시간: " + (System.currentTimeMillis() - start) + "ms");
    }

    static void main(String[] args) {
        exam6();
        // interrupt() / isInterrupted() / interrupted()
       /* 1. 실행 중단 요청
        2. 중지 알림 체크
        3. 중단 여부 알림을 확인 받고 그 여부를 다시 되돌려 실행 상태로 바꿈*/
    }

}
