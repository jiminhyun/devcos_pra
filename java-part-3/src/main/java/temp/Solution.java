package temp;

import java.awt.*;
import java.util.*;
import java.util.List;


class Solution {
    static class Node {
        int idx;
        int play;

        public int getPlay() {
            return play;
        }

        public int getIdx() {
            return idx;
        }

        public Node(int idx, int play) {
            this.idx = idx;
            this.play = play;
        }
    }

    static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.getPlay()-o1.getPlay();
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> cls = new HashMap<>(); //누가 가장 많은지 map
        HashMap<String, PriorityQueue<Node>> cls2 = new HashMap<>();
        int[] answer;
        List<Integer> changeAnswer = new ArrayList<>();
        for (int i = 0; i < plays.length; i++) {
            cls.put(genres[i], cls.getOrDefault(genres[i], 0) + plays[i]);
            cls2.computeIfAbsent(genres[i], k -> new PriorityQueue<>(new NodeComparator()))
                    .add(new Node(i, plays[i]));
        }
        List<String> keyList = cls.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .toList(); //많은 순서대로 key정렬
        for(String i: keyList) {
            Node checkINode= null;
            checkINode = cls2.get(i).poll();
            if(checkINode == null) {
                continue;
            } else {
                changeAnswer.add(checkINode.getIdx());
            }
            checkINode = cls2.get(i).poll();
            if(checkINode == null) {
                continue;
            } else {
                changeAnswer.add(checkINode.getIdx());
            }
        }
        answer = changeAnswer.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        return answer;
    }
}

//class Solution {
//    static class Disk {
//        int num; //순서
//        int rqTime; //요청 시간
//        int tkTime; //소요시간
//
//        public int getNum() {
//            return num;
//        }
//
//        public int getRqTime() {
//            return rqTime;
//        }
//
//        public int getTkTime() {
//            return tkTime;
//        }
//
//        public Disk(int num, int rqTime, int tkTime) {
//            this.num = num;
//            this.rqTime = rqTime;
//            this.tkTime = tkTime;
//        }
//    }
//
//    static class DiskComparator implements Comparator<Disk> {
//        @Override
//        public int compare(Disk o1, Disk o2) {
//            if (o1.tkTime == o2.tkTime) {
//                if(o1.rqTime == o2.rqTime) {
//                    return o1.num - o2.num;
//                } else {
//                    return o1.rqTime - o2.rqTime;
//                }
//            } else {
//                return o1.tkTime - o2.tkTime;
//            }
//        }
//    }
//
//    public int solution(int[][] jobs) {
//        List<Disk> diskList = new ArrayList<>();
//        int tempI=0;
//        for (int[] i:jobs) {
//            diskList.add(new Disk(tempI++,i[0],i[1]));
//        }
//        diskList.sort((a,b)->a.getRqTime()- b.getRqTime());
//        int jobSize = jobs.length;
//        int count = 0; //완료된 작업수
//        int idx = 0;
//        int answer = 0; //계산할 값
//        int pov = 0; //시점
//        Disk temp;
//        PriorityQueue<Disk> dk = new PriorityQueue<>(new DiskComparator());
//        while (count < jobSize) {
//            while (idx<jobSize && pov >= diskList.get(idx).getRqTime()) {
//                dk.offer(diskList.get(idx));
//                idx++;
//                }//다음 것이 시점보다 느릴경우 컷
//            if(dk.isEmpty()) {
//                pov=diskList.get(idx).getRqTime();
//            } else {
//                temp = dk.poll();
//                pov+=temp.getTkTime();
//                answer += pov-temp.getRqTime();
//                count++;
//            }
//
//            // 만들어진 큐로 하나 씩 진행
//
//        }
//
//        return answer/jobSize;
//    }
//}
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