package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.ChessGame;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class QueenTest {
    Board board;

    @BeforeEach
    void setUp(){
        board = new Board();
    }

    @Test
    @DisplayName("퀸 기물 이동 테스트")
    void move() {
        // given
        String sample =
                "........" +
                "........" +
                "........" +
                "....Q..." +
                "........" +
                "........" +
                "........" +
                "........";
        ChessGame.initialize(board, sample);

        // when
        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("e5"), new Board.Position("a3"))
        );
        ChessGame.move(board, new Board.Position("e5"), new Board.Position("e8"));

        // then


    }
}
