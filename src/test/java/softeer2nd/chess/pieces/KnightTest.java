package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.BoardView;
import softeer2nd.chess.GameManager;
import softeer2nd.chess.Board.Position;
import softeer2nd.chess.pieces.concrete.Knight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.exception.ExceptionMessage.IMPOSSIBLE_MOVEMENT;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class KnightTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }


    @Test
    @DisplayName("나이트 기물의 움직임 테스트")
    void moveKnight() {
        // given
        String knightSample = appendNewLine(
                "........",
                "........",
                "....N...",
                "........",
                "........",
                "........",
                "........",
                "........"
        );


        String expect = appendNewLine(
                "...N....",
                "........",
                "........",
                "........",
                "........",
                "........",
                "........",
                "........"
        );

        board.initialize(knightSample);

        // when
        GameManager.move(board, new Position("e6"), new Position("d8"));
        Piece result1 = board.findPiece(new Position("d8"));

        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> GameManager.move(board, new Position("d8"), new Position("d7"))
        );

        // then
        assertEquals(IMPOSSIBLE_MOVEMENT, illegalArgumentException.getMessage());
        assertEquals(result1, Knight.createBlackKnight());
        assertEquals(expect, BoardView.showBoard(board));
    }

}
