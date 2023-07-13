package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.BoardView;
import softeer2nd.chess.GameManager;
import softeer2nd.chess.Board.Position;
import softeer2nd.chess.pieces.concrete.Bishop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.exception.ExceptionMessage.IMPOSSIBLE_MOVEMENT;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class BishopTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("비숍 기물의 움직임 테스트")
    void moveBishop() {
        // given
        String bishopSample =
                appendNewLine(
                        "........",
                        "........",
                        "....B...",
                        "........",
                        "........",
                        "........",
                        "........",
                        "........");

        String expect =
                appendNewLine(
                        "......B.",
                        "........",
                        "........",
                        "........",
                        "........",
                        "........",
                        "........",
                        "........");
        board.initialize(bishopSample);

        // when
        GameManager.move(board, new Position("e6"), new Position("g8"));
        Piece result1 = board.findPiece(new Position("g8"));

        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> GameManager.move(board, new Position("g8"), new Position("g7"))
        );

        // then
        assertEquals(IMPOSSIBLE_MOVEMENT, illegalArgumentException.getMessage());
        assertEquals(result1, Bishop.createBlackBishop());
        assertEquals(expect, BoardView.showBoard(board));
    }
}
