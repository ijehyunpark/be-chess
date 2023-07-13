package softeer2nd.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.Position;

import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.exception.ExceptionMessage.WRONG_TURN;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class GameManagerTest {

    @Test
    @DisplayName("자신의 턴에 상대 기물 조작")
    void turnTest() {
        //given
        GameManager chessGame = new GameManager();
        chessGame.start();

        String expect = appendNewLine(
                "RNBQKBNR",
                ".PPPPPPP",
                "P.......",
                "........",
                "........",
                "p.......",
                ".ppppppp",
                "rnbqkbnr"
        );

        //when
        RuntimeException wrongTurnExceptionBlack = assertThrows(
                RuntimeException.class,
                () -> chessGame.move(new Position("a2"), new Position("a3"))
        );
        chessGame.move(new Position("a7"), new Position("a6"));


        RuntimeException wrongTurnExceptionWhite = assertThrows(
                RuntimeException.class,
                () -> chessGame.move(new Position("a6"), new Position("a5"))
        );
        chessGame.move(new Position("a2"), new Position("a3"));


        //then
        assertEquals(WRONG_TURN, wrongTurnExceptionBlack.getMessage());
        assertEquals(WRONG_TURN, wrongTurnExceptionWhite.getMessage());

        assertEquals(expect, chessGame.showBoard());
    }

    @Test
    @DisplayName("상대 편 킹 기물이 제거 여부를 반환하는지 테스트")
    void checkKingCount() {
        //given
        String sample = appendNewLine(
                "RNBQKBNR",
                "PPPPPPPP",
                ".....k..",
                "........",
                "........",
                "........",
                "........",
                "........"
        );
        Board board = new Board();
        board.initialize(sample);
        GameManager chessGame = new GameManager(board);
        chessGame.start();

        //when
        boolean isFinish = chessGame.move(new Position("e7"), new Position("f6"));

        //then
        assertTrue(isFinish);
    }

}
