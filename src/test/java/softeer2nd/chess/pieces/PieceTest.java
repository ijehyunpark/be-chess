package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.BoardView;
import softeer2nd.chess.GameManager;
import softeer2nd.chess.Board.Position;

import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.Board.BoardView.showBoard;
import static softeer2nd.chess.exception.ExceptionMessage.*;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class PieceTest {

    Board board;

    @BeforeEach
    void setUp() {
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

        Piece blank = BlankPiece.createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(BlankPiece.Type.NO_PIECE, blank.getPieceType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Piece.Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getPieceType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getPieceType());
    }

    @Test
    @DisplayName("기물의 색깔 확인 테스트")
    void color() {
        Piece blackPawn = PieceFactory.createPiece(BlankPiece.Type.PAWN, BlankPiece.Color.BLACK);
        Piece whitePawn = PieceFactory.createPiece(BlankPiece.Type.PAWN, BlankPiece.Color.WHITE);

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
        String sample = appendNewLine(
                ".K......",
                "........",
                "K....KN.",
                "........",
                "........",
                "........",
                "........",
                ".......K");

        String expect = appendNewLine(
                "........",
                "..K.....",
                ".....KN.",
                "........",
                "K.......",
                "........",
                "........",
                ".......K"
        );


        board.initialize(sample);

        // when
        GameManager.move(board, new Position("b8"), new Position("b7"));
        GameManager.move(board, new Position("b7"), new Position("c7"));

        assertThrows(
                IllegalArgumentException.class,
                () -> GameManager.move(board, new Position("c7"), new Position("a8"))
        );

        GameManager.move(board, new Position("a6"), new Position("a5"));
        GameManager.move(board, new Position("a5"), new Position("a4"));
        assertThrows(
                IllegalArgumentException.class,
                () -> GameManager.move(board, new Position("h1"), new Position("h0"))
        );
        assertThrows(
                IllegalArgumentException.class,
                () -> GameManager.move(board, new Position("h1"), new Position("i1"))
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> GameManager.move(board, new Position("f6"), new Position("g6"))
        );

        // then
        assertEquals(expect, showBoard(board));
    }

    @Test
    @DisplayName("재귀적 이동 기물 이동 테스트")
    void moveRecursive() {
        // given
        String sample = appendNewLine(
                "........",
                "........",
                "Q....QN.",
                "........",
                "........",
                "........",
                "........",
                ".......Q"
        );

        String expect = appendNewLine(
                "........",
                "........",
                "......N.",
                "........",
                "....Q...",
                "Q.......",
                "........",
                ".....Q.."
        );


        board.initialize(sample);

        // when
        GameManager.move(board, new Position("a6"), new Position("a8"));
        GameManager.move(board, new Position("a8"), new Position("a6"));
        GameManager.move(board, new Position("a6"), new Position("a5"));
        GameManager.move(board, new Position("a5"), new Position("a3"));


        assertThrows(
                IllegalArgumentException.class,
                () -> GameManager.move(board, new Position("f6"), new Position("g6"))
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> GameManager.move(board, new Position("h1"), new Position("d4"))
        );
        GameManager.move(board, new Position("h1"), new Position("e4"));
        GameManager.move(board, new Position("f6"), new Position("f1"));


        // then
        assertEquals(expect, showBoard(board));
    }


    @Test
    @DisplayName("기물을 넘어갈 수 없으며 색깔이 다른 기물 위치로 이동하면 해당 기물이 제거되어야 한다.")
    void remove() {
        // given
        String sample = appendNewLine(
                "........",
                "........",
                ".q...Q..",
                "........",
                "........",
                "........",
                "........",
                "........"
        );


        String expect = appendNewLine(
                "........",
                "........",
                ".Q......",
                "........",
                "........",
                "........",
                "........",
                "........"
        );

        board.initialize(sample);

        // when
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> GameManager.move(board, new Position("f6"), new Position("a6"))
        );
        GameManager.move(board, new Position("f6"), new Position("b6"));

        // then
        assertEquals(IMPOSSIBLE_MOVEMENT, illegalArgumentException.getMessage());
        assertEquals(expect, BoardView.showBoard(board));
    }

    @Test
    @DisplayName("같은 색 기물은 제거되면 안된다.")
    void notRemove() {
        // given
        String sample = appendNewLine(
                "........",
                "........",
                ".Q...Q..",
                "........",
                "........",
                "........",
                "........",
                "........"
        );


        String expect = appendNewLine(
                "........",
                "........",
                ".Q...Q..",
                "........",
                "........",
                "........",
                "........",
                "........"
        );

        board.initialize(sample);

        // when
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> GameManager.move(board, new Position("f6"), new Position("a6"))
        );

        // then
        assertEquals(expect, BoardView.showBoard(board));
        assertEquals(IMPOSSIBLE_MOVEMENT, illegalArgumentException.getMessage());
    }


    @Test
    @DisplayName("빈 기물의 움직임 테스트는 실패해야 합니다")
    void moveEmpty() {
        // given
        String expect = appendNewLine(
                "........",
                "........",
                "........",
                "........",
                "........",
                "........",
                "........",
                "........"
        );

        board.initializeEmpty();

        // when
        IllegalArgumentException illegalArgumentException = assertThrows(
                IllegalArgumentException.class,
                () -> GameManager.move(board, new Position("e6"), new Position("g7"))
        );

        // then
        assertEquals(expect, BoardView.showBoard(board));
        assertEquals(MOVE_NOT_MOVABLE_PIECE, illegalArgumentException.getMessage());
    }
}
