package homework;

import java.util.Scanner;

public class Start {

    public static void main(String[] args) {
        MemberRepository members = new MemberRepository();
        ConsoleViewer viewer = new ConsoleViewer();
        viewer.printPricePlan(members);
        System.out.println("총 가용 수는 "+members.getTotalCnt()+"명입니다.");

        while (true) {
            int check;
            try {
                check = Integer.parseInt(viewer.printMenu(members));
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요");
                continue;
            }
            switch (check) {
                case 1: viewer.addViewer(members); break;
                case 2: viewer.selectEmailViewer(members); break;
                case 3: viewer.selectNameViewer(members); break;
                case 4: members.viewAll(); break;
                case 5: viewer.memberModifyViewer(members); break;
                case 6: viewer.deleteMemberViewer(members); break;
                case 7:
                    System.out.println("프로그램 종료");return;
                default:
                    System.out.println("잘못눌렀습니다.");
            }
        }

    }


}
