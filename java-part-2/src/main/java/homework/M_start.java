package homework;

import java.util.Scanner;

// 자판기 시작점
public class M_start {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M_vendingmachine machine = new M_vendingmachine();

        while (true) {
            machine.printMenu();
            System.out.print("원하는 메뉴를 선택하세요 > ");
            int choice = sc.nextInt();
            if(choice >= 1 && choice <=5) {
                machine.buy(choice);
            } else if(choice == 6) {
                System.out.print("넣을 금액 > ");
                machine.insertMoney(sc.nextInt());
            } else if (choice == 7) {
                System.out.println("\n잔돈 " + machine.returnMoney() + "가 반환되었습니다.");
                return;
            } else {
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }
    }
}
