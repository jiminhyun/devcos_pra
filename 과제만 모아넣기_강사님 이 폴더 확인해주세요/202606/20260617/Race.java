package temp2;

public class Race {
    private volatile boolean over = false;

    boolean isOver() {
        return over;
    }

    synchronized void winnerPirnt(String name) {
        over = true;
        System.out.println("*** 우승: "+ name +" ***");
    }

}
