package org.example;

public class Start_modify {
    static final int TARGET = 3;
    public static void main(String[] args) {
        int check;
        BingoGame_modify.play();
        BingoGame_modify player = new BingoGame_modify();
        BingoGame_modify computer = new BingoGame_modify();
        BingoGame_modify.makeBoard(player);
        BingoGame_modify.makeBoard(computer);
        while(true) {
            player.printBoard(player);
            check = player.playerPick(player);
            player.mark(check);
            computer.mark(check);
            System.out.println("▶ 내가 부른 숫자: "+ check);
            check = computer.computerPick(computer);
            player.mark(check);
            computer.mark(check);
            System.out.println("▶ 컴퓨터가 부른 숫자: "+ check);
            if(BingoGame_modify.countBingo(player) >= TARGET && BingoGame_modify.countBingo(computer) >= TARGET) {
                System.out.println("무승부입니다!");
                System.out.println("플레이어판");
                player.printBoard(player);
                System.out.println("컴퓨터판");
                player.printBoard(computer);
                return;
            }else if(BingoGame_modify.countBingo(player) >= TARGET) {
                System.out.println("플레이어 승리입니다!");
                System.out.println("플레이어판");
                player.printBoard(player);
                System.out.println("컴퓨터판");
                player.printBoard(computer);
                return;
            }else if(BingoGame_modify.countBingo(computer) >= TARGET) {
                System.out.println("컴퓨터 승리입니다!");
                System.out.println("플레이어판");
                player.printBoard(player);
                System.out.println("컴퓨터판");
                player.printBoard(computer);
                return;
            } else {
                System.out.println("현재 빙고 줄 -> 나: "+BingoGame_modify.countBingo(player)+"줄,   컴퓨터: "+BingoGame_modify.countBingo(computer)+"줄");
            }
        }

    }
}
