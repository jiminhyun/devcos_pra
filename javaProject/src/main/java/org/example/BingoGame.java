package org.example;

import java.util.*;

public class BingoGame {
    static Scanner sc = new Scanner(System.in);
    static final int SIZE = 5;        // 판 크기 5x5
    static final int MAX = 25;        // 숫자 1~25

    int[][] board = new int [5][5];
    boolean [][] marked = new boolean[5][5];
    boolean[] called = new boolean[26];
    Random random = new Random();

    static void play() {
        System.out.println("===== 빙고 게임 =====");
        System.out.println("컴퓨터와 번갈아 숫자를 불러 빙고를 완성하세요!");
    }

    static void makeBoard(BingoGame player) {
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

    void printBoard(int[][] board, boolean[][]marked) {
        System.out.println("===== 내 빙고판 =====");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(marked[i][j]) System.out.print("[ ★]");
                else System.out.print("[ "+board[i][j]+"]");
            }
            System.out.println();
        }
    }

    int mark(BingoGame player, BingoGame computer, int num) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(player.board[i][j] == num)  {
                    player.marked[i][j] = true;
                    player.called[num] = true;
                }
            }
        }
        for (int k = 0; k < SIZE; k++) {
            for (int l = 0; l < SIZE; l++) {
                if(computer.board[k][l] == num)  {
                    computer.marked[k][l] = true;
                    computer.called[num] = true;
                }
            }
        }
        return num;
    }

    static int countBingo (BingoGame player) {
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

    int playerPick(BingoGame player) {
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

    int computerPick(BingoGame computer) {
        int num;
        do {num = computer.random.nextInt(MAX) + 1;}
        while (computer.called[num]);
        return num;
    }

}
