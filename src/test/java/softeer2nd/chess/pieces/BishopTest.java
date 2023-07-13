package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.BoardView;
import softeer2nd.chess.Board.ChessGame;
import softeer2nd.chess.pieces.concrete.Bishop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.utils.StringUtils.NEWLINE;

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
                "........" +
                        "........" +
                        "....B..." +
                        "........" +
                        "........" +
                        "........" +
                        "........" +
                        "........";

        String expect =
                "......B." + NEWLINE +
                        "........" + NEWLINE +
                        "........" + NEWLINE +
                        "........" + NEWLINE +
                        "........" + NEWLINE +
                        "........" + NEWLINE +
                        "........" + NEWLINE +
                        "........" + NEWLINE;
        ChessGame.initialize(board, bishopSample);

        // when
        ChessGame.move(board, new Board.Position("e6"), new Board.Position("g8"));
        Piece result1 = board.findPiece(new Board.Position("g8"));

        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("g8"), new Board.Position("g7"))
        );

        // then
        assertEquals(result1, Bishop.createBlackBishop());
        assertEquals(expect, BoardView.showBoard(board));
    }
}
