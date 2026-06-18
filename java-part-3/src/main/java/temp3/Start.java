package temp3;

import java.util.Scanner;

public class Start {

    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        F_account_book book = new F_account_book_impl(sc);

        while (true) {
            System.out.println("===== 가계부 =====");
            System.out.println("1. 내역 추가");
            System.out.println("2. 내역 조회");
            System.out.println("3. 전체 삭제");
            System.out.println("4. 내역 삭제");
            System.out.println("5. 종료");
            System.out.println("번호 입력 : ");

            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1: book.addAccount(); break;
                case 2: book.showAccount(); break;
                case 3: book.deleteAll(); break;
                case 4: book.deleteItem(); break;
                case 5:
                    System.out.println("종료합니다.");
                    return;
                default:
                    System.out.println("번호가 잘못되었습니다.");
            }
        }

    }
}
