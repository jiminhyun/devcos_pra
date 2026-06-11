package homework;

import java.util.Scanner;

public class ConsoleViewer {
    static Scanner sc = new Scanner(System.in);

    public void printPricePlan(MemberRepository members) {
        while (true) {
            System.out.println("[요금제를 선택하세요]\n" +
                    "[1]Lite : 10명 [2]Basic : 20명 [3]Premium : 30명 [다른 숫자] 기본 10명입니다.");
            int check;
            try {
                check = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요");
                continue;
            }

            switch (check) {
                case 1: case 2: case 3: members.setTotalCnt(check); return;
                default:
                    System.out.println("제대로 된 숫자를 입력하세요");
            }

        }
    }

    public String printMenu(MemberRepository members) {
        System.out.println("[수행할 업무를 선택하세요 - 현재 회원수 :"+members.getMemberCnt()+"/"+members.getTotalCnt()+ "\n" +
                "[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)\n" +
                "[4]회원전체조회 [5]회원정보 수정 [6]회원삭제\n" +
                "[7]프로그램 종료\n" +
                ">");
        return sc.nextLine();
    }

    public void addViewer(MemberRepository members) {
        String name, email, phoneNumber;
        System.out.println("이름을 입력하세요");
        name=sc.nextLine();
        System.out.println("이메일을 입력하세요");
        email=sc.nextLine();
        System.out.println("전화번호를 입력하세요");
        phoneNumber=sc.nextLine();
        members.memberAdd(new Member(name,email,phoneNumber));
    }

    public void selectEmailViewer(MemberRepository members) {
        System.out.println("조회할 이메일을 입력하세요.");
        String email = sc.nextLine();
        members.selectEmail(email);
    }

    public void selectNameViewer(MemberRepository members) {
        System.out.println("조회할 이메일을 입력하세요.");
        String name = sc.nextLine();
        members.selectName(name);
    }

    public void memberModifyViewer(MemberRepository members) {
        System.out.println("조회할 이름을 입력하세요.");
        String selectEmail = sc.nextLine();

        Member member = members.checkEmail(selectEmail);
        if(member == null) {
            System.out.println("회원정보가 없습니다.");
        } else {


            String name, email, phoneNumber;
            System.out.println("정보를 수정합니다");
            System.out.println("이름을 입력하세요");
            name=sc.nextLine();
            System.out.println("이메일을 입력하세요");
            email=sc.nextLine();
            System.out.println("전화번호를 입력하세요");
            phoneNumber=sc.nextLine();
            member.updateInfo(new Member(name,email,phoneNumber));
            System.out.println("성공적으로 수정되었습니다.");
        }
    }

    public void deleteMemberViewer(MemberRepository members) {
        System.out.println("조회할 이메일을 입력하세요.");
        String email = sc.nextLine();
        members.deleteMember(email);
    }
}
