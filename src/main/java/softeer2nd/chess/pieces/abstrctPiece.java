package softeer2nd.chess.pieces;

import softeer2nd.chess.Board.Board;

import java.util.List;

/**
 * 체스 게임에 사용되는 각 기물들을 정의하고 있다.
 */
public class abstrctPiece implements Piece {
    private final Color color;
    private final Type type;
    private static final abstrctPiece BLANK_PIECE_INSTANCE = new abstrctPiece();

    private abstrctPiece() {
        this.color = Color.NO_COLOR;
        this.type = Type.NO_PIECE;
    }
    protected abstrctPiece(Color color, Type type){
        this.color = color;
        this.type = type;
    }

    /**
     * 원시적인 기물의 싱글톤 인스턴스를 가져온다. <br/>
     * 해당 기물은 어떠한 기물종류에도 포함되자 않고, 색깔을 보유하지 않는다. <br/>
     * 보드판의 빈 칸을 표현하기 위해 사용된다.
     */
    public static abstrctPiece createBlank() {
        return BLANK_PIECE_INSTANCE;
    }

    /**
     * 새로운 기물을 생성한다.
     */
    public static abstrctPiece createPiece(final Type type, final Color color) {
        switch (type){
            case PAWN:
                return color == Color.BLACK ?
                        Pawn.createBlackPawn() : Pawn.createWhitePawn();
            case KNIGHT:
                return color == Color.BLACK ?
                        Knight.createBlackKnight() : Knight.createWhiteKnight();
            case BISHOP:
                return color == Color.BLACK ?
                        Bishop.createBlackBishop() : Bishop.createWhiteBishop();
            case ROOK:
                return color == Color.BLACK ?
                        Rook.createBlackRook() : Rook.createWhiteRook();
            case QUEEN:
                return color == Color.BLACK ?
                        Queen.createBlackQueen() : Queen.createWhiteQueen();
            case KING:
                return color == Color.BLACK ?
                        King.createBlackKing() : King.createWhiteKing();
            default:
                throw new IllegalArgumentException("타입이 허용되지 않습니다.");
        }
    }

    /**
     * 테스트를 사용하기 위한 메소드이다. <br/>
     * 표현식을 받아 새로운 체스말을 생성한다. <br/>
     * 표현식은 {@link abstrctPiece.Type#representation} 에서 확인할 수 있다.
     */
    public static abstrctPiece createPiece(char representation) {
        boolean isBlack = Character.isUpperCase(representation);
        representation = Character.toLowerCase(representation);
        for (Type type : Type.values()) {
            if (type.getWhiteRepresentation() == representation) {
                return type == Type.NO_PIECE ? createBlank() : createPiece(type, isBlack ? Color.BLACK : Color.WHITE);
            }
        }
        throw new IllegalArgumentException("잘못된 표현식입니다. : " + representation);
    }

    protected void verifyTargetMove(Board.Position target, List<Direction> moveAble){
        boolean isMoveAble = moveAble.stream()
                .anyMatch(direction ->
                        target.getYPos() == direction.getY() &&
                        target.getXPos() == direction.getX());

        if(!isMoveAble)
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 범위입니다.");
    }

    public void verifyMove(Board.Position source, Board.Position target) {
        throw new IllegalArgumentException("정의되지 않는 기물(빈 기물)은 이동할 수 없습니다.");
    }

    public Color getColor(){
        return this.color;
    }
    public Type getPieceType() {
        return this.type;
    };

    public char getRepresentation() {
        return color == Color.BLACK ?
                type.getBlackRepresentation() : type.getWhiteRepresentation();
    }

    public boolean isBlack() {
        return getColor() == Color.BLACK;
    }

    public boolean isWhite() {
        return getColor() == Color.WHITE;
    }
}
