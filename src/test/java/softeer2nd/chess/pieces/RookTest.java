package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.BoardView;
import softeer2nd.chess.Board.ChessGame;
import softeer2nd.chess.pieces.concrete.Rook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static softeer2nd.chess.utils.StringUtils.NEWLINE;

public class RookTest {

    Board board;

    @BeforeEach
    void setUp(){
        board = new Board();
    }


    @Test
    @DisplayName("룩 기물의 움직임 테스트")
    void moveRook() {
        // given
        String rookSample =
                "........" +
                "........" +
                "....R..." +
                "........" +
                "........" +
                "........" +
                "........" +
                "........";

        String expect =
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                ".....R.." + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........"+ NEWLINE;
        ChessGame.initialize(board, rookSample);

        // when
        ChessGame.move(board, new Board.Position("e6"), new Board.Position("e8"));
        Piece result1 = board.findPiece(new Board.Position("e8"));
        ChessGame.move(board, new Board.Position("e8"), new Board.Position("e5"));
        Piece result2 = board.findPiece(new Board.Position("e5"));


        ChessGame.move(board, new Board.Position("e5"), new Board.Position("a5"));
        Piece result3 = board.findPiece(new Board.Position("a5"));
        ChessGame.move(board, new Board.Position("a5"), new Board.Position("f5"));
        Piece result4 = board.findPiece(new Board.Position("f5"));

        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("f5"), new Board.Position("a1"))
        );

        // then
        assertEquals(result1, Rook.createBlackRook());
        assertEquals(result2, Rook.createBlackRook());
        assertEquals(result3, Rook.createBlackRook());
        assertEquals(result4, Rook.createBlackRook());
        assertEquals(expect, BoardView.showBoard(board));
    }
}
