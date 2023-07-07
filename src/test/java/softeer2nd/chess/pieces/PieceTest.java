package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.BoardView;
import softeer2nd.chess.Board.ChessGame;

import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.Board.BoardView.showBoard;
import static softeer2nd.chess.utils.StringUtils.NEWLINE;

class PieceTest {

    Board board;

    @BeforeEach
    void setUp(){
        board = new Board();
    }

    @Test
    void createPiece() {
        verifyPiece(PieceFactory.createPiece(BlankPiece.Type.PAWN, BlankPiece.Color.WHITE), PieceFactory.createPiece(BlankPiece.Type.PAWN, BlankPiece.Color.BLACK), BlankPiece.Type.PAWN);
        verifyPiece(PieceFactory.createPiece(BlankPiece.Type.KNIGHT, BlankPiece.Color.WHITE), PieceFactory.createPiece(BlankPiece.Type.KNIGHT, BlankPiece.Color.BLACK), BlankPiece.Type.KNIGHT);
        verifyPiece(PieceFactory.createPiece(BlankPiece.Type.BISHOP, BlankPiece.Color.WHITE), PieceFactory.createPiece(BlankPiece.Type.BISHOP, BlankPiece.Color.BLACK), BlankPiece.Type.BISHOP);
        verifyPiece(PieceFactory.createPiece(BlankPiece.Type.ROOK, BlankPiece.Color.WHITE), PieceFactory.createPiece(BlankPiece.Type.ROOK, BlankPiece.Color.BLACK), BlankPiece.Type.ROOK);
        verifyPiece(PieceFactory.createPiece(BlankPiece.Type.QUEEN, BlankPiece.Color.WHITE), PieceFactory.createPiece(BlankPiece.Type.QUEEN, BlankPiece.Color.BLACK), BlankPiece.Type.QUEEN);
        verifyPiece(PieceFactory.createPiece(BlankPiece.Type.KING, BlankPiece.Color.WHITE), PieceFactory.createPiece(BlankPiece.Type.KING, BlankPiece.Color.BLACK), BlankPiece.Type.KING);

        BlankPiece blank = BlankPiece.createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(BlankPiece.Type.NO_PIECE, blank.getPieceType());
    }

    private void verifyPiece(final BlankPiece whitePiece, final BlankPiece blackPiece, final BlankPiece.Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getPieceType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getPieceType());
    }

    @Test
    @DisplayName("기물의 색깔 확인 테스트")
    void color() {
        BlankPiece blackPawn = PieceFactory.createPiece(BlankPiece.Type.PAWN, BlankPiece.Color.BLACK);
        BlankPiece whitePawn = PieceFactory.createPiece(BlankPiece.Type.PAWN, BlankPiece.Color.WHITE);

        assertTrue(blackPawn.isBlack());
        assertFalse(blackPawn.isWhite());
        assertFalse(whitePawn.isBlack());
        assertTrue(whitePawn.isWhite());
    }

    @Test
    @DisplayName("기물의 타입 별 표현 방식 동작 테스트")
    void getRepresentationPerPiece() {
        assertEquals('p', BlankPiece.Type.PAWN.getWhiteRepresentation());
        assertEquals('P', BlankPiece.Type.PAWN.getBlackRepresentation());
    }

    @Test
    @DisplayName("단순 이동 기물 이동 테스트")
    void moveNonRecursive() {
        // given
        String sample =
                ".K......" +
                "........" +
                "K....KN." +
                "........" +
                "........" +
                "........" +
                "........" +
                ".......K";

        String expect =
                "........\n" +
                "..K.....\n" +
                ".....KN.\n" +
                "........\n" +
                "K.......\n" +
                "........\n" +
                "........\n" +
                ".......K\n";

        ChessGame.initialize(board, sample);

        // when
        ChessGame.move(board, new Board.Position("b8"), new Board.Position("b7"));
        ChessGame.move(board, new Board.Position("b7"), new Board.Position("c7"));

        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("c7"), new Board.Position("a8"))
        );

        ChessGame.move(board, new Board.Position("a6"), new Board.Position("a5"));
        ChessGame.move(board, new Board.Position("a5"), new Board.Position("a4"));
        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("h1"), new Board.Position("h0"))
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("h1"), new Board.Position("i1"))
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("f6"), new Board.Position("g6"))
        );

        // then
        assertEquals(expect, showBoard(board));
    }

    @Test
    @DisplayName("재귀적 이동 기물 이동 테스트")
    void moveRecursive() {
        // given
        String sample =
                "........" +
                "........" +
                "Q....QN." +
                "........" +
                "........" +
                "........" +
                "........" +
                ".......Q";

        String expect =
                "........\n" +
                "........\n" +
                "......N.\n" +
                "........\n" +
                "....Q...\n" +
                "Q.......\n" +
                "........\n" +
                ".....Q..\n";

        ChessGame.initialize(board, sample);

        // when
        ChessGame.move(board, new Board.Position("a6"), new Board.Position("a8"));
        ChessGame.move(board, new Board.Position("a8"), new Board.Position("a6"));
        ChessGame.move(board, new Board.Position("a6"), new Board.Position("a5"));
        ChessGame.move(board, new Board.Position("a5"), new Board.Position("a3"));


        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("f6"), new Board.Position("g6"))
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("h1"), new Board.Position("d4"))
        );
        ChessGame.move(board, new Board.Position("h1"), new Board.Position("e4"));
        ChessGame.move(board, new Board.Position("f6"), new Board.Position("f1"));


        // then
        assertEquals(expect, showBoard(board));
    }


    @Test
    @DisplayName("기물을 넘어갈 수 없으며 색깔이 다른 기물 위치로 이동하면 해당 기물이 제거되어야 한다.")
    void remove() {
        // given
        String sample =
                "........" +
                "........" +
                ".q...Q.." +
                "........" +
                "........" +
                "........" +
                "........" +
                "........";

        String expect =
                "........" + NEWLINE +
                "........" + NEWLINE +
                ".Q......" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE;
        ChessGame.initialize(board, sample);

        // when
        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("f6"), new Board.Position("a6"))
        );
        ChessGame.move(board, new Board.Position("f6") ,new Board.Position("b6"));

        // then
        assertEquals(expect, BoardView.showBoard(board));
    }

    @Test
    @DisplayName("같은 색 기물은 제거되면 안된다.")
    void notRemove() {
        // given
        String sample =
                "........" +
                "........" +
                ".Q...Q.." +
                "........" +
                "........" +
                "........" +
                "........" +
                "........";

        String expect =
                "........" + NEWLINE +
                "........" + NEWLINE +
                ".Q...Q.." + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE;
        ChessGame.initialize(board, sample);

        // when
        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("f6"), new Board.Position("a6"))
        );

        // then
        assertEquals(expect, BoardView.showBoard(board));
    }


    @Test
    @DisplayName("없는 기물의 움직임 테스트는 실패해야 합니다")
    void moveEmpty() {
        // given
        String expect =
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE +
                "........" + NEWLINE;
        ChessGame.initializeEmpty(board);

        // when
        assertThrows(
                IllegalArgumentException.class,
                () -> ChessGame.move(board, new Board.Position("e6"), new Board.Position("g7"))
        );

        // then
        assertEquals(expect, BoardView.showBoard(board));
    }
}