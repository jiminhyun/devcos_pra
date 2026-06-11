package homework;

public class M_fanta implements M_drink {
    private final String name = "환타";
    private final int money = 300;
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
        System.out.println("환타가 나왔습니다.");
    }
}