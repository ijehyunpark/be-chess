package softeer2nd;

import softeer2nd.chess.Board.Position;
import softeer2nd.chess.GameManager;

import java.util.Scanner;

import static softeer2nd.chess.utils.StringUtils.NEW_LINE;

public class Main {

    private static final GameManager chessGame = new GameManager();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean scanLoop = true;

        while (scanLoop) {
            try {
                // 명령어 입력
                String command = scanner.nextLine();

                if (command.equals("start")) {
                    start();
                } else if (command.equals("end")) {
                    scanLoop = false;
                } else if (command.startsWith("move ")) {
                    String[] commandArgs = command.split(" ");
                    if (move(commandArgs)) {
                        System.out.println("상대 킹 기물이 제거되었습니다.");
                        scanLoop = false;
                    }
                } else {
                    System.out.println("잘못된 명령어 입니다.");
                }
            } catch (RuntimeException exception) {
                System.out.println(exception.getLocalizedMessage());
            }
        }
    }

    private static void start() {
        chessGame.start();
        System.out.println(chessGame.showBoard());
    }

    private static boolean move(String[] commandArgs) {
        if (commandArgs.length != 3) {
            System.out.println("잘못된 인자 개수입니다. 다음과 같이 입력해 주세요." + NEW_LINE + "move [이동 전] [이동 후]");
        }

        boolean isRemovedOpponentKing = chessGame.move(new Position(commandArgs[1]), new Position(commandArgs[2]));
        System.out.println(chessGame.showBoard());
        return isRemovedOpponentKing;
    }
}
