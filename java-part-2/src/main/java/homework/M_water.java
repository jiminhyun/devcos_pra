package homework;

public class M_water implements M_drink {
    private final String name = "물";
    private final int money = 200;
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
        System.out.println("물이 나왔습니다.");
    }
}