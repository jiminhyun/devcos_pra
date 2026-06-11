package org.example;

import java.util.*;

public class BingoGame_modify {
    static Scanner sc = new Scanner(System.in);
    static final int SIZE = 5;        // 판 크기 5x5
    static final int MAX = SIZE * SIZE;        // 숫자 1~25

    int[][] board = new int [5][5];
    boolean [][] marked = new boolean[5][5];
    boolean[] called = new boolean[MAX+1];
    Random random = new Random();

    static void play() {
        System.out.println("===== 빙고 게임 =====");
        System.out.println("컴퓨터와 번갈아 숫자를 불러 빙고를 완성하세요!");
    }

    static void makeBoard(BingoGame_modify player) {
        List<Integer> number = new ArrayList<>();
        for (int i = 1; i <=MAX ; i++) number.add(i);
        Collections.shuffle(number);
        int idx = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                player.board[i][j] = number.get(idx++);
            }
        }

    }

    void printBoard(BingoGame_modify player) {
        System.out.println("===== 내 빙고판 =====");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(player.marked[i][j]) System.out.print("[ ★]");
                else System.out.print("[ "+player.board[i][j]+"]");
            }
            System.out.println();
        }
    }

    void mark(int num) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(this.board[i][j] == num)  {
                    this.marked[i][j] = true;
                    this.called[num] = true;
                }
            }
        }
    }

    static int countBingo (BingoGame_modify player) {
        int count = 2;
        boolean check = true;
        for (int i = 0; i < SIZE; i++) { //가로
            check = true;
            for (int j = 0; j < SIZE; j++) {
                if(!player.marked[i][j]) {
                    check = false;
                    break;
                }
            }
            if (check) count++;
        }
        for (int i = 0; i < SIZE; i++) { //세로
            check = true;
            for (int j = 0; j < SIZE; j++) {
                if(!player.marked[j][i]) {
                    check = false;
                    break;
                }
            }
            if (check) count++;
        }

        for (int i = 0; i < SIZE; i++) { //대각선 \
            if(!player.marked[i][i]) {
                count--;
                break;
            }
        }

        for (int i = 0; i < SIZE; i++) { //대각선 /
            if(!player.marked[i][SIZE-i-1]) {
                count--;
                break;
            }
        }
        return count;
    }

    int playerPick(BingoGame_modify player) {
        while (true) {
            System.out.println("부를 숫자 입력 (1~25) > ");
            int num;
            try {
                num = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력하세요.");
                continue;
            }
            if (num <= 0 || num > 25) {
                System.out.println("범위초과");
            } else if (player.called[num]) System.out.println("이미 부른 숫자입니다.");
            else return num;
        }

    }

    int computerPick(BingoGame_modify computer) {
        int num;
        do {num = computer.random.nextInt(MAX) + 1;}
        while (computer.called[num]);
        return num;
    }

}
