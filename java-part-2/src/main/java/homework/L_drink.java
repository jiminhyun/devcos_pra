package homework;

/*
    모든 음료의 공통 틀
    이름, 가격
    음료가 나올 때 동작 -> dispense(): 추상메서드
 */
public abstract class L_drink {
    protected String name;
    protected int price;

    //기본 생성자 이외를 구현하면 디폴트로 생성 안함
    public L_drink(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    //음료가 나올 때 동작
    public abstract void dispense();
}
