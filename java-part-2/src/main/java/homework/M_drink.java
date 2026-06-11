package homework;

/*
    모든 음료의 공통 틀
    이름, 가격
    음료가 나올 때 동작 -> dispense(): 추상메서드
 */
public interface M_drink {

    public abstract int getMoney();
    public int getStock();

    public void setStock();
    //음료가 나올 때 동작
    public abstract void dispense();
}
