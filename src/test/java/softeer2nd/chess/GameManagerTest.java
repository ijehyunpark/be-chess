package softeer2nd.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.exception.ExceptionMessage.WRONG_TURN;
import static softeer2nd.chess.pieces.Piece.Type.PAWN;
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
}
