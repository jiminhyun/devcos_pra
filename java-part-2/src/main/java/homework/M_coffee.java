package homework;

public class M_coffee implements M_drink {
    private final String name = "커피";
    private final int money = 400;
    private int stock = 9;

    public int getMoney() {
        return money;
    }

    public int getStock() {
        return stock;
    }

    public void setStock() {
        stock--;
    }

    @Override
    public void dispense() {
        System.out.println("커피가 나왔습니다.");
    }
}