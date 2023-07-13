package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.ChessGame;
import softeer2nd.chess.Board.Position;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class QueenTest {
    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("퀸 기물 이동 테스트")
    void move() {
        // given
        String sample = appendNewLine(
                "........",
                "........",
                "........",
                "....Q...",
                "........",
                "........",
                "........",
                "........"
        );

        board.initialize(sample);

        // when
        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Position("e5"), new Position("a3"))
        );
        ChessGame.move(board, new Position("e5"), new Position("e8"));

        // then


    }
}
