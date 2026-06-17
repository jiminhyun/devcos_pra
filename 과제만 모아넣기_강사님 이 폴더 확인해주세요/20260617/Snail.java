package temp2;

import java.util.Random;

public class Snail extends Thread{

    private String name;
    private int position = 0;
    private final int FINISH = 30;
    private Random random = new Random();
    private Race race;

    public Snail(String name, Race race) {
        this.name = name;
        this.race = race;
    }

    @Override
    public void run() {
        while (position < FINISH && !race.isOver()) {
            position += random.nextInt(3)+1;
            if (position > FINISH) position = FINISH;
            printProgress();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if(position >= FINISH) {
            race.winnerPirnt(name);
        }
    }

    private void printProgress() {
        StringBuffer bar = new StringBuffer();
        for (int i = 0; i < position; i++) {
            bar.append("=");
        }
        System.out.println(name+": "+bar+">");
    }
}
