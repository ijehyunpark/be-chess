package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.BoardView;
import softeer2nd.chess.Board.ChessGame;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.utils.StringUtils.NEW_LINE;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class PawnTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("폰 움직임 테스트")
    void movePawn() {
        // given
        String sample = appendNewLine(
                "........",
                "........",
                "........",
                "........",
                "...Pp...",
                "........",
                "........",
                "........"
        );

        String expect = appendNewLine(
                "........",
                "........",
                "........",
                "....p...",
                "........",
                "...P....",
                "........",
                "........"
        );

        board.initialize(sample);

        // when
        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("d4"), new Board.Position("d5"))
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("d4"), new Board.Position("c4"))
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("d4"), new Board.Position("e4"))
        );
        ChessGame.move(board, new Board.Position("d4"), new Board.Position("d3"));
        ChessGame.move(board, new Board.Position("e4"), new Board.Position("e5"));

        // then
        assertEquals(expect, BoardView.showBoard(board));
    }

    @Test
    @DisplayName("앙파상 테스트")
    void moveEnPassant() {
        // given
        String sample = appendNewLine(
                "........",
                "........",
                "........",
                "....p...",
                "...P....",
                "........",
                "........",
                "........"
        );

        String expect = appendNewLine(
                "........",
                "........",
                "........",
                "....P...",
                "........",
                "........",
                "........",
                "........"
        );

        board.initialize(sample);

        // when
        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("d4"), new Board.Position("c5"))
        );
        ChessGame.move(board, new Board.Position("d4"), new Board.Position("e5"));

        // then
        assertEquals(expect, BoardView.showBoard(board));
    }

}
