package softeer2nd;

import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.ChessGame;

import java.util.Scanner;

import static softeer2nd.chess.Board.BoardView.showBoard;
import static softeer2nd.chess.utils.StringUtils.NEWLINE;

public class Main {

    private static final Board board = new Board();
    private static boolean isStart = false;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean scanLoop = true;
        while(scanLoop) {
            // 명령어 입력
            String command = scanner.nextLine();

            if (command.equals("start")) {
                start();
            } else if (command.equals("end")) {
                scanLoop = false;
            } else if(command.startsWith("move")) {
                String[] commandArgs = command.split(" ");
                try {
                    move(commandArgs);
                } catch (IllegalArgumentException exception) {
                    System.out.println(exception.getLocalizedMessage());
                }
            } else{
                System.out.println("잘못된 명령어 입니다.");
            }
        }
    }

    private static void start() {
        ChessGame.initialize(board);
        isStart = true;

        System.out.println(showBoard(board));
    }

    private static void move(String[] commandArgs) {
        if(commandArgs.length != 3) {
            System.out.println("잘못된 인자 개수입니다. 다음과 같이 입력해 주세요." + NEWLINE + "move [이동 전] [이동 후]");
        }
        else if(!isStart){
            System.out.println("게임을 시작해 주세요.");
        }
        ChessGame.move(board, new Board.Position(commandArgs[1]), new Board.Position(commandArgs[2]));

        System.out.println(showBoard(board));
    }
}
