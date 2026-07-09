package temp;

import java.util.Scanner;

public class Main_member {
    static Scanner sc = new Scanner(System.in);
    static void main(String[] args) {
        PricePlan pricePlan;// 요금제
        while (true) {
            System.out.println("[요금제를 선택하세요] \n"+
                    "[1]Lite:10 [2]Basic:20 [3]Premium:30");
            try {
                pricePlan = PricePlan.selectPlan(Integer.parseInt(sc.nextLine().trim()));
            } catch (NumberFormatException e) {
                System.out.println("지정된 값을 입력하세요");
                continue;
            }
            if (pricePlan != null) break;
        } //요금제 선택
        MemberManager manager = new MemberManager(pricePlan.getCapacity());
        while (true) {
            System.out.println("[수행할 업무를 선택하세요 - 현재 회원수 : " + manager.size() + "/" + manager.getCapacity() + "]\n" +
                    "[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)\n" +
                    "[4]회원전체조회 [5]회원정보 수정 [6]회원삭제\n" +
                    "[7]프로그램 종료");
            int checkMenu;
            try {
                checkMenu=Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("지정된 값을 입력하세요");
                continue;
            }
            Member member;
            String name, email, phone;
            switch (checkMenu) {
                case 1:
                    if (manager.isFull(pricePlan)) {
                        System.out.println("회원이 가득찼습니다.");
                    } else {
                        System.out.println("등급 [1]일반 [2]VIP");
                        int grade;
                        try {
                            grade = Integer.parseInt(sc.nextLine().trim());
                        } catch (NumberFormatException e) {
                            System.out.println("지정된 값을 입력하세요");
                            break;
                        }
                        if(grade != 1 && grade != 2) {
                            System.out.println("지정된 값을 입력하세요");
                            break;
                        }
                        System.out.print("이름 > ");
                        name = sc.nextLine();
                        System.out.print("이메일 > ");
                        email = sc.nextLine();
                        System.out.print("연락처 > ");
                        phone = sc.nextLine();

                        if (manager.existsEmail(email)) {
                            System.out.println("회원이 존재합니다.");
                        } else {
                            Member m = (grade == 1) ? new NormalMember(name, email, phone) :
                                    new VipMember(name, email, phone);
                            manager.add(m);
                        }
                    }
                    break;
                case 2:
                    System.out.println("이메일을 입력하세요");
                    email = sc.nextLine();
                    member = manager.findByEmail(email);
                    if (member != null) {
                        member.printInfo();
                    } else {
                        System.out.println("없는 회원입니다.");
                    }
                    break;
                case 3:
                    System.out.println("이름을 입력하세요");
                    name = sc.nextLine();
                    member = manager.findByName(name);
                    if (member != null) {
                        member.printInfo();
                    } else {
                        System.out.println("없는 회원입니다.");
                    }
                    break;
                case 4:
                    manager.printAll();
                    break;
                case 5:
                    System.out.println("수정할 이메일을 입력하세요");
                    String viewEmail = sc.nextLine();

                    if (!manager.existsEmail(viewEmail)) {
                        System.out.println("없는 회원입니다.");
                    } else {
                        System.out.println("새로지을 이름을 입력하세요");
                        name = sc.nextLine();
                        System.out.println("새로지을 이메일을 입력하세요");
                        email = sc.nextLine();
                        System.out.println("새로지을 전화번호를 입력하세요");
                        phone = sc.nextLine();

                        if (manager.update(viewEmail, name, email, phone)) {
                            System.out.println("성공적으로 수정되었습니다.");
                        }
                    }
                    break;
                case 6:
                    System.out.println("삭제할 이메일을 입력하세요");
                    email = sc.nextLine();
                    if (manager.delete(email)) {
                        System.out.println("성공적으로 삭제되었습니다.");
                    } else {
                        System.out.println("없는 회원입니다.");
                    }
                    break;
                case 7:

                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }

    }

}
