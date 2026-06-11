package org.example;

public class Start {
    static final int TARGET = 3;
    public static void main(String[] args) {
        int check;
        BingoGame.play();
        BingoGame player = new BingoGame();
        BingoGame computer = new BingoGame();
        BingoGame.makeBoard(player);
        BingoGame.makeBoard(computer);
        while(true) {
            player.printBoard(player.board, player.marked);
            check = player.mark(player, computer, player.playerPick(player));
            System.out.println("▶ 내가 부른 숫자: "+ check);
            check = computer.mark(player, computer, computer.computerPick(computer));
            System.out.println("▶ 컴퓨터가 부른 숫자: "+ check);
            if(BingoGame.countBingo(player) >= TARGET && BingoGame.countBingo(computer) >= TARGET) {
                System.out.println("무승부입니다!");
                return;
            }else if(BingoGame.countBingo(player) >= TARGET) {
                System.out.println("플레이어 승리입니다!");
                return;
            }else if(BingoGame.countBingo(computer) >= TARGET) {
                System.out.println("컴퓨터 승리입니다!");
                return;
            } else {
                System.out.println("현재 빙고 줄 -> 나: "+BingoGame.countBingo(player)+"줄,   컴퓨터: "+BingoGame.countBingo(computer)+"줄");
            }
        }

    }
}
