package org.example;
/*
반복문
어떤 작업이 반복적으로 수행하도록
 */
/*
    for(초기값, 조건식, 증감식) {
        true 시 수행문장
    }
 */

import javax.swing.plaf.BorderUIResource;
import java.util.Scanner;

public class H_loop {
    static void main(String[] args) {
        checkEvenMethod();
    }

    public static void exam1() {
        for (int i = 0; i<10; i++) {
            System.out.println("cnt : "+i);
        }
    }

    public static void exam2() {
        for (int i = 1; i<10; i++) {
            System.out.println("cnt : 2 *"+i+"="+(2*i));
        }
    }

    public static void multiplication_table () {
        for (int i = 2; i<10; i++) {
            for (int j = 1; j <10; j++) {
                System.out.println(i+" * "+j+" = "+(i*j));
            }
        }
    }
    public static void even_number () {
        for (int i = 0; i<=100; i++) {
            if(i%2==0) {
                System.out.println(i);
            }
        }
    }

    public static void test() {
        for (int i = 0; i <=100; i++) {
            if (i ==30) {
                System.out.println(i);
                break;
            } else {
                System.out.println(i);
            }
        }
    }
    /*
    for문 정의 방법과 continue와 break 사용법
     */
    public static void multiplication_revtable () {
        for (int i = 9; i > 0; i--) {
            for (int j = 1; j <10; j++) {
                System.out.println(i+" * "+j+" = "+(i*j));
            }
        }
    }
    public static void whileTest() {
        int i = 2;
        int j = 1;
        while (true) {
            if(j == 10) {
                break;
            } else {
                System.out.println(i+" * "+ j +" = " + (i*j));
                j++;
            }
        }
    }

    public static void whileTest2() {
        int i = 2;
        int j = 1;
        while (i <10) {
            while (j<10) {
                System.out.println(i+" * "+ j +" = " + (i*j));
                j++;
            }
            j=1;
            i++;
        }
    }

    static void check6() {
        Scanner sc = new Scanner(System.in);
        int checkNum;
        int sum = 0;
        while (true) {
            System.out.println("정수값을 입력하세요. 해당 n!수열의 합을 구합니다., 종료는 0을 눌러주세요");
            checkNum = sc.nextInt();
            if(checkNum == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            sum = checkNum * (checkNum+1) / 2;
            System.out.println("합계는 "+sum+"입니다.");
            sum=0;
        }
    }

    static void checkMultiply() {
        Scanner sc = new Scanner(System.in);
        int checkDan;
        while (true) {
            System.out.println("정수값을 입력하세요. 해당 구구단을 구합니다., 0을 입력시 종료합니다.");
            checkDan = sc.nextInt();
            if(checkDan == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            for (int i = 1; i < 10; i++) {
                System.out.println(checkDan + " * " + i + " = "+(checkDan*i));
            }
        }
    }
    static void checkEvenMethod() {
        Scanner sc = new Scanner(System.in);
        int checkEven;
        while (true) {
            System.out.println("정수값을 입력하세요. 해당 밑의 짝수들을 구합니다., 0을 입력시 종료합니다.");
            checkEven = sc.nextInt();
            while (checkEven > 0) {
                if (checkEven%2 == 0) {
                System.out.println(checkEven);
                }
                checkEven--;
                if(checkEven == 0) {
                    System.out.println(checkEven);
                    checkEven = -1;
                }
            }
            if (checkEven == 0) break;
        }
    }

    // do while은 최소 한번은 실행
    static void practice() {
        do {
            System.out.println("dd");
        }while (true);
    }
}

