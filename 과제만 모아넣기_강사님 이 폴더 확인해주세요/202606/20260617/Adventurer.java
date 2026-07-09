package temp2;

public class Adventurer extends Thread{
    private final String name;
    private final Dungeon dungeon;
    @Override
    public void run() {
        dungeon.enter(name);
    }
    public Adventurer(Dungeon dungeon, String name) {
        this.name = name;
        this.dungeon = dungeon;
    }
}
