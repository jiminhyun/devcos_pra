package homework;
/*
    자판기
    현재 금액과 판매 음료들을 관리한다.
 */

public class L_vendingmachine {
    private int totalMoney;
    private L_drink[] drinks; // 다형성

    public L_vendingmachine() {
        totalMoney = 0;
        // 조상 타입에 자식 객체들을 넣은 것
        drinks = new L_drink[] {
                new L_coke(),
                new L_cidar(),
                new L_fanta(),
                new L_water()
        };
    }

    //돈 넣기
    public void insertMoney(int money) {
        totalMoney += money;
        System.out.println(money + "원을 넣었습니다.");
    }

    public void buy(int menuNumber) {
        L_drink drink = drinks[menuNumber-1];

        if(totalMoney < drink.getPrice()) {
            System.out.println("잔돈이 부족합니다.");
            return;
        }
        totalMoney -=drink.getPrice();
        drink.dispense();
    }

    public int returnMoney() {
        int extraMoney = totalMoney;
        totalMoney = 0;
        return extraMoney;
    }

    public void printMenu() {
        System.out.println("============== 자판기 ==============");
        System.out.println("[1]콜라 : 500  [2]사이다 : 500  [3]환타 : 300  [4]물 : 200");
        System.out.println("[5]돈 넣기  [6]종료");
        System.out.println("현재 금액 : " + totalMoney);
        System.out.println("====================================");
    }
}
