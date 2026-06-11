package homework;
/*
    자판기
    현재 금액과 판매 음료들을 관리한다.
 */

public class M_vendingmachine {
    private int totalMoney;
    private final M_drink[] drinks; // 다형성

    public M_vendingmachine() {
        totalMoney = 0;
        // 조상 타입에 자식 객체들을 넣은 것
        drinks = new M_drink[] {
                new M_coke(),
                new M_cidar(),
                new M_fanta(),
                new M_water(),
                new M_coffee()
        };
    }

    //돈 넣기
    public void insertMoney(int money) {
        if(money%10 == 0) {
            totalMoney += money;
            System.out.println(money + "원을 넣었습니다.");
        } else System.out.println("돈 규격이 안 맞습니다.");

    }

    public void buy(int menuNumber) {
        M_drink drink = drinks[menuNumber-1];

        if(totalMoney < drink.getMoney()) {
            System.out.println("잔돈이 부족합니다.");
            return;
        } else if (drink.getStock() == 0) {
            System.out.println("품절입니다");
            return;
        }
        drink.setStock();
        totalMoney -= drink.getMoney();
        drink.dispense();
    }

    public String returnMoney() {
        int[] units = {500, 100, 50, 10}; // 단위 배열
        int[] counts = new int[4];        // 개수 저장 배열

        for (int i = 0; i < units.length; i++) {
            counts[i] = totalMoney / units[i];
            totalMoney %= units[i];
        }

        return String.format("500원 %d개 100원 %d개 50원 %d개 10원 %d개 ",
                counts[0], counts[1], counts[2], counts[3]);
    }

    public void printMenu() {
        System.out.println("============== 자판기 ==============");
        System.out.println("[1]콜라 : 500  [2]사이다 : 500  [3]환타 : 300  [4]물 : 200  [5]커피 : 400");
        System.out.println("[6]돈 넣기  [7]종료");
        System.out.println("현재 금액 : " + totalMoney);
        System.out.println("====================================");
    }
}
