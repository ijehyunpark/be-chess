package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceTest {

    /**
     * 특정 색깔 폰이 생성되는지 검증한다.
     * @param color 검증할 폰 색깔을 나타낸다.
     */
    void verifyPawn(final Color color){
        Pawn pawn = new Pawn(color);
        assertEquals(color, pawn.getColor());
    }

    @Test
    @DisplayName("흰색 및 검은색 폰이 생성되어야 한다")
    void create() {
        verifyPawn(Color.WHITE);
        verifyPawn(Color.BLACK);
    }

    @Test
    void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(Color.WHITE, pawn.getColor());
    }

    @Test
    void create_piece() {
        verifyPiece(Piece.createPawn(Color.WHITE), Color.WHITE, PieceType.PAWN);
        verifyPiece(Piece.createPawn(Color.BLACK), Color.BLACK, PieceType.PAWN);

        verifyPiece(Piece.createKnight(Color.WHITE), Color.WHITE, PieceType.KNIGHT);
        verifyPiece(Piece.createKnight(Color.BLACK), Color.BLACK, PieceType.KNIGHT);

        verifyPiece(Piece.createBishop(Color.WHITE), Color.WHITE, PieceType.BISHOP);
        verifyPiece(Piece.createBishop(Color.BLACK), Color.BLACK, PieceType.BISHOP);

        verifyPiece(Piece.createRook(Color.WHITE), Color.WHITE, PieceType.ROOK);
        verifyPiece(Piece.createRook(Color.BLACK), Color.BLACK, PieceType.ROOK);

        verifyPiece(Piece.createQueen(Color.WHITE), Color.WHITE, PieceType.QUEEN);
        verifyPiece(Piece.createQueen(Color.BLACK), Color.BLACK, PieceType.QUEEN);

        verifyPiece(Piece.createKing(Color.WHITE), Color.WHITE, PieceType.KING);
        verifyPiece(Piece.createKing(Color.BLACK), Color.BLACK, PieceType.KING);
    }

    private void verifyPiece(final Piece piece, final Color color, final PieceType type) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getPieceType());
    }
}