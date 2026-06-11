package org.example;
// * 조건문
// 조건식의 연산결과에 따라 실행할 문장이 달라져서 프로그램의 실행흐름을 바꿀 수 있다.

import java.util.Scanner;

public class G_if_switch {

    // 1) if문
    // if (조건식 참) { 실행 }
    public static void exam1() {
        int score = 90;

        if (score >= 90) {
            // 조건식 참일 때 수행할 내용
            System.out.println("A학점 입니다.");
        }
    }

    // 1-1) if - else
    public static void exam2() {
        int score = 9;

        if (score >= 60) {
            System.out.println("합격입니다.");
        } else {
            System.out.println("불합격입니다.");
        }
    }

    // 1-2) if - else if - else
    public static void exam3() {
        System.out.println("점수를 입력해주세요.");
        Scanner sc = new Scanner(System.in);
        int score = sc.nextInt(); // 사용자 값을 입력받아주세요.

        if (score >= 90) {
            System.out.println("A학점 입니다.");
        } else if (score >= 80) {
            System.out.println("B학점 입니다.");
        } // C, D학점 복습
        else {
            System.out.println("F학점 입니다.");
        }

        // 여기
    }

    static void exam4() {
        System.out.println("점수를 입력해주세요.");
        Scanner sc = new Scanner(System.in);
        int score = sc.nextInt(); // 사용자 값을 입력받아주세요.

    }

    static void practice(int check) {
        switch (check/10) {
            case 10:
            case 9:
                System.out.println("A학점");
                break;
            case 8:
                System.out.println("B학점");
                break;
            case 7:
                System.out.println("C학점");
                break;
            default:
                System.out.println("F학점");
        }
    }
    static void practice2(int check) {
        switch (check/10) {
            case 10:
            case 9:
                System.out.println("A학점");
                break;
            case 8:
                System.out.println("B학점");
                break;
            case 7:
                System.out.println("C학점");
                break;
            default:
                System.out.println("F학점");
        }
    }

    //날짜는 CASE 묶고 동일하게 하면 된다.


    static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.println("너의 학점을 알려주세요");
        practice(sc.nextInt());
    }
}
