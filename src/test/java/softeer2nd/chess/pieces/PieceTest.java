package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    /**
     * 특정 색깔 폰이 생성되는지 검증한다.
     * @param color 검증할 폰 색깔을 나타낸다.
     */
    void verifyPawn(final Piece.Color color){
        Pawn pawn = new Pawn(color);
        assertEquals(color, pawn.getColor());
    }

    @Test
    @DisplayName("흰색 및 검은색 폰이 생성되어야 한다")
    void create() {
        verifyPawn(Piece.Color.WHITE);
        verifyPawn(Piece.Color.BLACK);
    }

    @Test
    void create_piece() {
        verifyPiece(Piece.createPawn(Piece.Color.WHITE), Piece.Color.WHITE, Piece.Type.PAWN);
        verifyPiece(Piece.createPawn(Piece.Color.BLACK), Piece.Color.BLACK, Piece.Type.PAWN);

        verifyPiece(Piece.createKnight(Piece.Color.WHITE), Piece.Color.WHITE, Piece.Type.KNIGHT);
        verifyPiece(Piece.createKnight(Piece.Color.BLACK), Piece.Color.BLACK, Piece.Type.KNIGHT);

        verifyPiece(Piece.createBishop(Piece.Color.WHITE), Piece.Color.WHITE, Piece.Type.BISHOP);
        verifyPiece(Piece.createBishop(Piece.Color.BLACK), Piece.Color.BLACK, Piece.Type.BISHOP);

        verifyPiece(Piece.createRook(Piece.Color.WHITE), Piece.Color.WHITE, Piece.Type.ROOK);
        verifyPiece(Piece.createRook(Piece.Color.BLACK), Piece.Color.BLACK, Piece.Type.ROOK);

        verifyPiece(Piece.createQueen(Piece.Color.WHITE), Piece.Color.WHITE, Piece.Type.QUEEN);
        verifyPiece(Piece.createQueen(Piece.Color.BLACK), Piece.Color.BLACK, Piece.Type.QUEEN);

        verifyPiece(Piece.createKing(Piece.Color.WHITE), Piece.Color.WHITE, Piece.Type.KING);
        verifyPiece(Piece.createKing(Piece.Color.BLACK), Piece.Color.BLACK, Piece.Type.KING);
    }

    private void verifyPiece(final Piece piece, final Piece.Color color, final Piece.Type type) {
        assertEquals(color, piece.getColor());
        assertEquals(type, piece.getPieceType());
    }

    @Test
    @DisplayName("Piece 색깔 확인 테스트")
    void color() {
        Piece blackPawn = Piece.createPawn(Piece.Color.BLACK);
        Piece whitePawn = Piece.createPawn(Piece.Color.WHITE);

        assertTrue(blackPawn.isBlack());
        assertFalse(blackPawn.isWhite());
        assertFalse(whitePawn.isBlack());
        assertTrue(whitePawn.isWhite());
    }

    @Test
    @DisplayName("Piece 타입 별 표현 방식 동작 테스트")
    void getRepresentationPerPiece() {
        assertEquals('p', Piece.Type.PAWN.getWhiteRepresentation());
        assertEquals('P', Piece.Type.PAWN.getBlackRepresentation());
    }
}