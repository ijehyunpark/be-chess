package softeer2nd.chess.pieces;

/**
 * 체스 게임에 사용되는 각 기물들을 정의하고 있다.
 */
public class Piece {

    public enum Color {
        BLACK, WHITE, NO_COLOR;
    }

    public enum Type {
        KING('k', 0), QUEEN('q', 9), ROOK('r',5),
        BISHOP('b', 3), KNIGHT('n', 2.5), PAWN('p', 1),
        NO_PIECE('.', 0);

        /**
         * 기물의 표현 방식 <br/>
         * 흑색일 경우 대문자로 치환된다.
         */
        private final char representation;
        private final double defaultScore;

        Type(char representation, double defaultScore) {
            this.representation = representation;
            this.defaultScore = defaultScore;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }

        public double getDefaultScore() {
            return defaultScore;
        }
    }

    private final Color color;
    private final Type type;
    private static final Piece BLANK_PIECE_INSTANCE = new Piece();

    private Piece() {
        this.color = Color.NO_COLOR;
        this.type = Type.NO_PIECE;
    }
    protected Piece(Color color, Type type){
        this.color = color;
        this.type = type;
    }

    /**
     * 원시적인 기물의 싱글톤 인스턴스를 가져온다. <br/>
     * 해당 기물은 어떠한 기물종류에도 포함되자 않고, 색깔을 보유하지 않는다. <br/>
     * 보드판의 빈 칸을 표현하기 위해 사용된다.
     */
    public static Piece createBlank() {
        return BLANK_PIECE_INSTANCE;
    }

    /**
     * 새로운 기물을 생성한다.
     */
    public static Piece createPiece(final Type type, final Color color) {
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
     * 표현식은 {@link Piece.Type#representation} 에서 확인할 수 있다.
     */
    public static Piece createPiece(char representation) {
        boolean isBlack = Character.isUpperCase(representation);
        representation = Character.toLowerCase(representation);
        for (Type type : Type.values()) {
            if (type.representation == representation) {
                return type == Type.NO_PIECE ? createBlank() : createPiece(type, isBlack ? Color.BLACK : Color.WHITE);
            }
        }
        throw new IllegalArgumentException("잘못된 표현식입니다. : " + representation);
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
