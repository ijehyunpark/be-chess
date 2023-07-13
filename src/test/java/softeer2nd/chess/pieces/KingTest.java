package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.GameManager;
import softeer2nd.chess.Board.Position;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.exception.ExceptionMessage.IMPOSSIBLE_MOVEMENT;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class KingTest {

    Board board;
    GameManager chessGame;

    @BeforeEach
    void setUp() {
        board = new Board();
        chessGame = new GameManager(board);
        chessGame.start();
    }

    @Test
    @DisplayName("킹 기물 이동 테스트")
    void move() {
        // given
        String sample = appendNewLine(
                "........",
                "........",
                "........",
                "....K...",
                "........",
                "........",
                "........",
                "........"
        );

        board.initialize(sample);

        // when
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> chessGame.move(new Position("e5"), new Position("e7"))
        );
        chessGame.move(new Position("e5"), new Position("e6"));

        // then
        assertEquals(IMPOSSIBLE_MOVEMENT, illegalArgumentException.getMessage());
    }

}

