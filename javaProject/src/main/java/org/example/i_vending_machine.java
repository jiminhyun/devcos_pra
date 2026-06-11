package org.example;

import java.util.Scanner;

//static은 객체 생성없이 에서드 영역에서 먼저 생성되기 때문에 메모리영역 생명주기가 달라서 static으로 선언된 메인 메서드에서는 static으로 선언하지 않은 메서드를 출력할려고하면 결국 오류가 발생하게 된다.
public class i_vending_machine {
    static final int COKE = 500, CIDER = 500, FANTA = 300, WATER = 200;
    static void main(String[] args) {
        int totalMoney = 0;
        while (true) {
            printMenu(totalMoney);

            int choice = getChoice();
            int result = -1;
            switch (choice) {
                case 1:
                    result = calcMoney(totalMoney, COKE);
                    if(result < 0){
                        calcMoneyException();
                    } else {
                        totalMoney = result;
                        System.out.println("현재 " + result +"원");
                    }
                    break;
                case 2:
                    result = calcMoney(totalMoney, CIDER);
                    if(result < 0){
                        calcMoneyException();
                    } else {
                        totalMoney = result;
                        System.out.println("현재 " + result +"원");
                    }
                    break;
                case 3:
                    result = calcMoney(totalMoney, FANTA);
                    if(result < 0){
                        calcMoneyException();
                    } else {
                        totalMoney = result;
                        System.out.println("현재 " + result +"원");
                    }
                    break;
                case 4:
                    result = calcMoney(totalMoney, WATER);
                    if(result < 0){
                        calcMoneyException();
                    } else {
                        totalMoney = result;
                        System.out.println("현재 " + result +"원");
                    }
                    break;
                case 5:
                    totalMoney+=getMoney();
                    break;
                case 6:
                    System.out.println("현재 금액 :" + totalMoney + "원");
                    return;
                default:
                    System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
                    break;
            }
        }
    }

    static int getChoice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("번호 입력하세요");
        return sc.nextInt();
    }

    public static void printMenu(int totalMoney) {
        System.out.println("============== 자판기 ==============");
        System.out.println("[1]콜라 : 500, [2]사이다 : 500, [3]환타 : 300, [4]물 : 200, [5]돈 넣기, [6]종료");
        System.out.println("현재 금액 : " + totalMoney +"원");
        System.out.println("====================================");
    }

    static int getMoney() {
        System.out.println("넣을 돈 입력");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    static int calcMoney(int totalMoney, int juice) {
        return totalMoney-juice;
    }

    public static void calcMoneyException() {
        System.out.println("돈 부족");
    }
}
