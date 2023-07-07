package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.BoardView;
import softeer2nd.chess.Board.ChessGame;
import softeer2nd.chess.pieces.concrete.Knight;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.utils.StringUtils.NEWLINE;

public class KnightTest {
    Board board;

    @BeforeEach
    void setUp(){
        board = new Board();
    }


    @Test
    @DisplayName("나이트 기물의 움직임 테스트")
    void moveKnight() {
        // given
        String knightSample =
                "........" +
                "........" +
                "....N..." +
                "........" +
                "........" +
                "........" +
                "........" +
                "........";

        String expect =
                "...N...." + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE;
        ChessGame.initialize(board, knightSample);

        // when
        ChessGame.move(board, new Board.Position("e6"), new Board.Position("d8"));
        Piece result1 = board.findPiece(new Board.Position("d8"));

        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("d8"), new Board.Position("d7"))
        );

        // then
        assertEquals(result1, Knight.createBlackKnight());
        assertEquals(expect, BoardView.showBoard(board));
    }
}
