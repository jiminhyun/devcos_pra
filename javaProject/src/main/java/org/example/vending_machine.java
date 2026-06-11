package org.example;

import java.util.Scanner;

public class vending_machine {
    static final int COKE = 500, CIDER = 700, FANTA = 300, WATER = 200;

    public static void main(String[] args) {
        Scanner moneyScan = new Scanner(System.in);
        System.out.println("가지고 있는 돈을 입력하시오");
        int money = moneyScan.nextInt();
        printMenu(money);
    }

    public static void printMenu(int totalMoney) {
        System.out.println("==========================자판기==========================");
        Scanner menuNumScan = new Scanner(System.in);
        while(true) {
            System.out.println("[1]콜라=500원 [2]사이다=700원 [3]환타=300원 [4]물=200원 [5]돈 넣기 [6] 좋료");
            int menuNum = -1;
            try {
                menuNum = menuNumScan.nextInt();
            } catch (Exception e) {
                System.out.println("정수를 입력하세요");
                menuNumScan.nextLine();
                System.out.println();
                continue;
            }
            if(menuNum == 6) {
                break;
            } else if (menuNum == 5) {
                System.out.println("추가로 돈을 입력하시오");
                Scanner sumMoneyScan = menuNumScan;
                int sumMoney = 0;
                try{
                    sumMoney = sumMoneyScan.nextInt();
                } catch (Exception e) {
                    System.out.println("정수를 입력하세요");
                    menuNumScan.nextLine();
                    System.out.println();
                }
                totalMoney+=sumMoney;
            }else if (menuNum >= 1 && menuNum <= 4) {
                if (check(totalMoney, menuNum)>=0) {
                    totalMoney = check(totalMoney, menuNum);
                } else {
                    System.out.println("돈이 부족합니다.");
                }
            } else {
                System.out.println("번호가 잘못되었습니다.");
            }
            System.out.println("현재 금액 :" + totalMoney + "원");
        }


    }

    public static int check(int totalMoney, int menuNum) {
        switch (menuNum) {
            case 1:
                totalMoney-=COKE;
                System.out.println("콜라 구매 완료");
                break;
            case 2:
                totalMoney-=CIDER;
                System.out.println("사이다 구매 완료");
                break;
            case 3:
                totalMoney-=FANTA;
                System.out.println("환타 구매 완료");
                break;
            case 4:
                totalMoney-=WATER;
                System.out.println("물 구매 완료");
                break;
            default:
                System.out.println("정수를 입력하세요");
        }
        return totalMoney;
    }
}

