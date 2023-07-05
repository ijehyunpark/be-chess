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
    void createPiece() {
        verifyPiece(Piece.createPiece(Piece.Type.PAWN, Piece.Color.WHITE), Piece.createPiece(Piece.Type.PAWN, Piece.Color.BLACK), Piece.Type.PAWN);
        verifyPiece(Piece.createPiece(Piece.Type.KNIGHT, Piece.Color.WHITE), Piece.createPiece(Piece.Type.KNIGHT, Piece.Color.BLACK), Piece.Type.KNIGHT);
        verifyPiece(Piece.createPiece(Piece.Type.BISHOP, Piece.Color.WHITE), Piece.createPiece(Piece.Type.BISHOP, Piece.Color.BLACK), Piece.Type.BISHOP);
        verifyPiece(Piece.createPiece(Piece.Type.ROOK, Piece.Color.WHITE), Piece.createPiece(Piece.Type.ROOK, Piece.Color.BLACK), Piece.Type.ROOK);
        verifyPiece(Piece.createPiece(Piece.Type.QUEEN, Piece.Color.WHITE), Piece.createPiece(Piece.Type.QUEEN, Piece.Color.BLACK), Piece.Type.QUEEN);
        verifyPiece(Piece.createPiece(Piece.Type.KING, Piece.Color.WHITE), Piece.createPiece(Piece.Type.KING, Piece.Color.BLACK), Piece.Type.KING);

        Piece blank = Piece.createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(Piece.Type.NO_PIECE, blank.getPieceType());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Piece.Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getPieceType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getPieceType());
    }

    @Test
    @DisplayName("Piece 색깔 확인 테스트")
    void color() {
        Piece blackPawn = Piece.createPiece(Piece.Type.PAWN, Piece.Color.BLACK);
        Piece whitePawn = Piece.createPiece(Piece.Type.PAWN, Piece.Color.WHITE);

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