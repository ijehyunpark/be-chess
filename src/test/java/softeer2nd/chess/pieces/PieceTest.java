package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PieceTest {

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
}