package org.example;

import java.util.Scanner;

public class K_number_management
{
    static Scanner sc = new Scanner(System.in);
    static int totalCnt = 0; // 회원 총 가용 수
    static int memberCnt = 0; // 실 회원 수

    static void main(String[] args) {
        totalCnt = printPricePlan();
        String[][] members = new String[totalCnt][3];
        System.out.println("총 가용 수는 "+totalCnt+"명입니다.");
        while (true) {
            int check;
            check = printMenu();
            switch (check) {
                case 1: memberAdd(members); break;
                case 2: selectEmail(members); break;
                case 3: selectName(members); break;
                case 4: viewAll(members); break;
                case 5: memberModify(members); break;
                case 6: deleteMember(members); break;
                case 7:
                    System.out.println("프로그램 종료");return;
                default:
                    System.out.println("잘못눌렀습니다.");
            }
        }
    }

    static boolean checkEmail(String email, String[][]members) {
        for (int i = 0; i < memberCnt; i++) {
            if(email.equals(members[i][1])) {
                System.out.println("이메일이 중복됩니다.");
                return false;
            }
        }
        return true;
    }

    static void memberAdd(String[][]members) {
        String name, email, phoneNumber;
        System.out.println("이름을 입력하세요");
        name=sc.nextLine();
        System.out.println("이메일을 입력하세요");
        email=sc.nextLine();
        System.out.println("전화번호를 입력하세요");
        phoneNumber=sc.nextLine();
        if(memberCnt == totalCnt) System.out.println("회원이 가득찼습니다.");
        else if(checkEmail(email, members)) {
            members[memberCnt][0] = name;
            members[memberCnt][1] = email;
            members[memberCnt][2] = phoneNumber;
            memberCnt++;
        }
    }

    static void selectEmail(String[][] members) {
        System.out.println("조회할 이메일을 입력하세요.");
        String email = sc.nextLine();

        for (int i = 0; i < memberCnt; i++) {
            if (members[i][1].equals(email)) {
                System.out.println("이름: "+members[i][0] +" 이메일: "+members[i][1]+" 전화번호: "+members[i][2]);
                return;
            }
        }
        System.out.println("회원정보 없습니다.");
    }

    static void selectName(String[][] members) {
        System.out.println("조회할 이름을 입력하세요.");
        String name = sc.nextLine();

        for (int i = 0; i < memberCnt; i++) {
            if (members[i][0].equals(name)) {
                System.out.println("이름: "+members[i][0] +" 이메일: "+members[i][1]+" 전화번호: "+members[i][2]);
                return;
            }
        }
        System.out.println("회원정보 없습니다.");
    }

    static void viewAll(String[][] members){
        for (int i = 0; i < memberCnt; i++) {
            System.out.println("이름: "+members[i][0] +" 이메일: "+members[i][1]+" 전화번호: "+members[i][2]);
        }
    }

    static void memberModify(String[][] members) {
        System.out.println("조회할 이메일을 입력하세요.");
        String selectEmail = sc.nextLine();

        for (int i = 0; i < memberCnt; i++) {
            if (members[i][1].equals(selectEmail)) {
                String name, email, phoneNumber;
                System.out.println("정보를 수정합니다");
                System.out.println("이름을 입력하세요");
                name=sc.nextLine();
                System.out.println("이메일을 입력하세요");
                email=sc.nextLine();
                System.out.println("전화번호를 입력하세요");
                phoneNumber=sc.nextLine();
                members[i][0] = name;
                members[i][1] = email;
                members[i][2] = phoneNumber;
                return;
            }
        }
        System.out.println("회원정보가 없습니다.");
    }

    static void deleteMember(String[][] members) {
        System.out.println("조회할 이메일을 입력하세요.");
        String selectEmail = sc.nextLine();

        for (int i = 0; i < memberCnt; i++) {
            if (members[i][1].equals(selectEmail)) {
                for (int j = i; j < memberCnt-1; j++) {
                    members[i][0] = members[i+1][0];
                    members[i][1] = members[i+1][1];
                    members[i][2] = members[i+1][2];
                }
                members[memberCnt][0]= null;
                members[memberCnt][1]= null;
                members[memberCnt][2]= null;
                memberCnt--;
                return;
            }
        }
        System.out.println("회원정보가 없습니다.");
    }

    static int printPricePlan() {
        System.out.println("[요금제를 선택하세요]\n" +
                "[1]Lite : 10명 [2]Basic : 20명 [3]Premium : 30명 [다른 숫자] 기본 10명입니다.");
        return switch (sc.nextInt()) {
            case 2 -> 20;
            case 3 -> 30;
            default -> 10;
        };
    }

    static int printMenu() {
        System.out.println("[수행할 업무를 선택하세요 - 현재 회원수 :"+memberCnt+"/"+totalCnt+ "\n" +
                "[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)\n" +
                "[4]회원전체조회 [5]회원정보 수정 [6]회원삭제\n" +
                "[7]프로그램 종료\n" +
                ">");
        int info = sc.nextInt();
        sc.nextLine();
        return info;
    }

}
