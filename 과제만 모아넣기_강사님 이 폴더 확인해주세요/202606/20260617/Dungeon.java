package temp2;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Dungeon {
    private final int capacity;
    private final Semaphore slots;

    public Dungeon(int capacity) {
        this.capacity = capacity;
        slots = new Semaphore(capacity);
    }


    public void enter(String name) {
        System.out.println(name+ " 던전 입장 대기...");
        try {
            slots.acquire();
            System.out.println("[입장] "+ name + " (남은 자리: "+ slots.availablePermits()+"/"+capacity+")");
            Thread.sleep((int) (Math.random()*1000+1000));
            int gold = (int) (Math.random()*1000+1000);
            System.out.println("[클리어] " + name +" → " + gold +" 골드 획득");
        } catch (InterruptedException e) {
            //Thread.currentThread().interrupt(); 종료됬는지 밖에 알리고 싶을때
        } finally {
            System.out.println("[퇴장] " + name);
            slots.release();
        }

    }
}
