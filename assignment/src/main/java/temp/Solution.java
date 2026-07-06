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