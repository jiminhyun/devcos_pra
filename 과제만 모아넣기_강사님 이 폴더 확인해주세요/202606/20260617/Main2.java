package temp2;

public class Main2 {
    public static void main(String[] args) {
        Dungeon dungeon = new Dungeon(2);
        String[] names = {"마법사", "전사", "궁수", "거너", "광전사"};
        for(String i : names) {
            new Adventurer(dungeon, i).start();
        }
        /*Adventurer ad1 = new Adventurer(dungeon,"마법사");
        Adventurer ad2 = new Adventurer(dungeon,"전사");
        Adventurer ad3 = new Adventurer(dungeon,"궁수");
        Adventurer ad4 = new Adventurer(dungeon,"거너");
        Adventurer ad5 = new Adventurer(dungeon,"광전사");
        ad1.start();
        ad2.start();
        ad3.start();
        ad4.start();
        ad5.start();*/
    }

}
