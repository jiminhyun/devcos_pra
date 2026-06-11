package homework;

import java.util.*;

public class Up_down_game {
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    static int correct_answer = rand.nextInt(100) + 1;   // 0~99 에 +1 → 1~100
    static int answer;

    static void main(String[] args) {
        int count = 0;
        System.out.println("숫자를 맞혀보세요! (1 ~ 100)");
        while(true) {
            System.out.print("입력 > ");
            try {
                answer = sc.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력하세요.");
                continue;
            }
            if (answer < 1 || answer > 100) {
                System.out.println("1~100 사이로 입력해 주세요.");
            } else if(answer > correct_answer){
                System.out.println("DOWN! 더 작은 수입니다.");
                count++;
            }
            else if (answer < correct_answer){
                System.out.println("UP! 더 큰 수입니다.");
                count++;
            } else {
                break;
            }

        }
        System.out.println("정답입니다! "+count+"번 만에 맞혔어요.");
    }
}
