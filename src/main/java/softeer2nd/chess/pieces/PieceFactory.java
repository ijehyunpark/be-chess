package softeer2nd.chess.pieces;

import softeer2nd.chess.pieces.concrete.*;

public abstract class PieceFactory {
    /**
     * 테스트를 사용하기 위한 메소드이다. <br/>
     * 표현식을 받아 새로운 체스말을 생성한다. <br/>
     * 표현식은 {@link Piece.Type} 에서 확인할 수 있다.
     */
    public static Piece createPiece(char representation) {
        boolean isBlack = Character.isUpperCase(representation);
        representation = Character.toLowerCase(representation);
        for (Piece.Type type : Piece.Type.values()) {
            if (type.getWhiteRepresentation() == representation) {
                return type == Piece.Type.NO_PIECE ? BlankPiece.createBlank() : createPiece(type, isBlack ? Piece.Color.BLACK : Piece.Color.WHITE);
            }
        }
        throw new IllegalArgumentException("잘못된 표현식입니다. : " + representation);
    }

    public static MovablePiece createPiece(final Piece.Type type, final Piece.Color color) {
        switch (type) {
            case PAWN:
                return color == Piece.Color.BLACK ?
                        Pawn.createBlackPawn() : Pawn.createWhitePawn();
            case KNIGHT:
                return color == Piece.Color.BLACK ?
                        Knight.createBlackKnight() : Knight.createWhiteKnight();
            case BISHOP:
                return color == Piece.Color.BLACK ?
                        Bishop.createBlackBishop() : Bishop.createWhiteBishop();
            case ROOK:
                return color == Piece.Color.BLACK ?
                        Rook.createBlackRook() : Rook.createWhiteRook();
            case QUEEN:
                return color == Piece.Color.BLACK ?
                        Queen.createBlackQueen() : Queen.createWhiteQueen();
            case KING:
                return color == Piece.Color.BLACK ?
                        King.createBlackKing() : King.createWhiteKing();
            default:
                throw new IllegalArgumentException("타입이 허용되지 않습니다.");
        }
    }
}
