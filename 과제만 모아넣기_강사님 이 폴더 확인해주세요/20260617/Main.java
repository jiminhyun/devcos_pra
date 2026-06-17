package temp2;

public class Main {
    public static void main(String[] args) {
        Race race = new Race();
        Snail snail1 = new Snail("달팽이1", race);
        Snail snail2 = new Snail("달팽이2", race);
        Snail snail3 = new Snail("달팽이3", race);
        snail1.start();
        snail2.start();
        snail3.start();
    }
}
