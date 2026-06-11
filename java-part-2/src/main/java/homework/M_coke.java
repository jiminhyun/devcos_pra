package homework;

public class M_coke implements M_drink {
    private final String name = "콜라";
    private final int money = 500;
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
        System.out.println("콜라가 나왔습니다.");
    }
}
