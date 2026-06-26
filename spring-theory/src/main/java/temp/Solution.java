package temp;

import java.util.*;


class Solution {
    public int[] solution(int[] progresses, int[] speeds, String e ) {
        int count = 0; //작업 시작해야하는 부분
        int dayCount = 0;//그날 끝난 작업량
        List<Integer> list = new ArrayList<>();
        int[] answer = {};
        while(count < speeds.length) {
            while (count <speeds.length && progresses[count] < 100) {
                for (int i = count; i < speeds.length; i++) {
                    progresses[i] +=speeds[i];
                }//1일차 종료
                while (count <speeds.length && progresses[count] >= 100) {
                    dayCount++;
                    count++;
                }// 끝난후 작업이 됬으면 넘기기
                if(dayCount !=0) {
                    list.add(dayCount);
                    dayCount = 0;
                }

            }//진척도가 100을 넘으면 작업을 끝낸다.
        }

        if(e.charAt(0) == e.charAt(1)) {

        }
        answer = list.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}