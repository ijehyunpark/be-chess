package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

    @Test
    void createPiece() {
        verifyPiece(abstrctPiece.createPiece(abstrctPiece.Type.PAWN, abstrctPiece.Color.WHITE), abstrctPiece.createPiece(abstrctPiece.Type.PAWN, abstrctPiece.Color.BLACK), abstrctPiece.Type.PAWN);
        verifyPiece(abstrctPiece.createPiece(abstrctPiece.Type.KNIGHT, abstrctPiece.Color.WHITE), abstrctPiece.createPiece(abstrctPiece.Type.KNIGHT, abstrctPiece.Color.BLACK), abstrctPiece.Type.KNIGHT);
        verifyPiece(abstrctPiece.createPiece(abstrctPiece.Type.BISHOP, abstrctPiece.Color.WHITE), abstrctPiece.createPiece(abstrctPiece.Type.BISHOP, abstrctPiece.Color.BLACK), abstrctPiece.Type.BISHOP);
        verifyPiece(abstrctPiece.createPiece(abstrctPiece.Type.ROOK, abstrctPiece.Color.WHITE), abstrctPiece.createPiece(abstrctPiece.Type.ROOK, abstrctPiece.Color.BLACK), abstrctPiece.Type.ROOK);
        verifyPiece(abstrctPiece.createPiece(abstrctPiece.Type.QUEEN, abstrctPiece.Color.WHITE), abstrctPiece.createPiece(abstrctPiece.Type.QUEEN, abstrctPiece.Color.BLACK), abstrctPiece.Type.QUEEN);
        verifyPiece(abstrctPiece.createPiece(abstrctPiece.Type.KING, abstrctPiece.Color.WHITE), abstrctPiece.createPiece(abstrctPiece.Type.KING, abstrctPiece.Color.BLACK), abstrctPiece.Type.KING);

        abstrctPiece blank = abstrctPiece.createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(abstrctPiece.Type.NO_PIECE, blank.getPieceType());
    }

    private void verifyPiece(final abstrctPiece whitePiece, final abstrctPiece blackPiece, final abstrctPiece.Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getPieceType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getPieceType());
    }

    @Test
    @DisplayName("기물의 색깔 확인 테스트")
    void color() {
        abstrctPiece blackPawn = abstrctPiece.createPiece(abstrctPiece.Type.PAWN, abstrctPiece.Color.BLACK);
        abstrctPiece whitePawn = abstrctPiece.createPiece(abstrctPiece.Type.PAWN, abstrctPiece.Color.WHITE);

        assertTrue(blackPawn.isBlack());
        assertFalse(blackPawn.isWhite());
        assertFalse(whitePawn.isBlack());
        assertTrue(whitePawn.isWhite());
    }

    @Test
    @DisplayName("기물의 타입 별 표현 방식 동작 테스트")
    void getRepresentationPerPiece() {
        assertEquals('p', abstrctPiece.Type.PAWN.getWhiteRepresentation());
        assertEquals('P', abstrctPiece.Type.PAWN.getBlackRepresentation());
    }
}