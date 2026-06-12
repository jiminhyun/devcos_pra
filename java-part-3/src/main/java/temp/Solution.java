package temp;

import java.util.*;


class Solution {
    public int[] solution(long n) {
        int cnt = 0;
        ArrayList<Integer> answer= new ArrayList<Integer>();
        while (n>0){
            answer.add((int) (n%10));
            n /=10;
            cnt++;
        };
        int[] answer2 = new int[cnt];
        for (int i = 0; i < answer.size(); i++) {
            answer2[i]=(int)answer.get(i);
        }
        return answer2;
    }
}
//class Solution {
//    public int solution(int[] queue1, int[] queue2) {
//        List<Integer> list = new ArrayList<Integer>();
//        Long check = 0L;
//        Long checkPanel=0L;
//        int left = 0;
//        int right = queue1.length;
//        int answer = 0;
//        for (int i :queue1) {
//            list.add(i);
//        }
//        for (int i :queue2) {
//            list.add(i);
//        }
//
//        for(Integer integers : list) {
//            check+=integers;
//        }
//
//        for (int j : queue1) {
//            checkPanel += j;
//        }
//
//        if(check %2 !=0) {
//            return -1;
//        }
//        while (true) {
//            if(checkPanel == check/2) {
//                return answer;
//            } else if(right >= list.size() || left >= list.size()) {
//                return -1;
//            } else if (checkPanel > check/2) {
//                checkPanel -=list.get(left++);
//                answer++;
//            } else if (checkPanel < check/2) {
//                checkPanel+=list.get(right++);
//                answer++;
//            }
//        }
//    }
//}