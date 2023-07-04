package softeer2nd;

import softeer2nd.chess.Board;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean scanLoop = true;
        while(scanLoop) {
            // 명령어 입력
            String command = scanner.nextLine();
            Board board;

            switch (command) {
                case "start":
                    board = new Board();
                    board.initialize();
                    System.out.println(board.print());
                    break;
                case "end":
                    scanLoop = false;
                    break;
            }
        }
    }
}