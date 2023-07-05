package softeer2nd.chess.pieces;

/**
 * 채스의 게임 말의 공통적인 구현체 및 생성 팩토리 메소드를 가지고 있다.
 */
public class Piece {

    /**
     * 각 체스말의 색깔을 나타낸다.
     */
    public enum Color {
        BLACK, WHITE, NO_COLOR;
    }

    /**
     * 체스 말의 종류를 나타낸다.
     */
    public enum Type {
        KING('k', 0), QUEEN('q', 9), ROOK('r',5),
        BISHOP('b', 3), KNIGHT('n', 2.5), PAWN('p', 1),
        NO_PIECE('.', 0);

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
     * 원시적인 체스 말을 생성한다.
     * @return 추가적인 기능을 제공하지 않는 원시적인 체스 말 객체
     */
    public static Piece createBlank() {
        return BLANK_PIECE_INSTANCE;
    }

    /**
     * 새로운 체스 말을 생성한다.
     * @param type 생성할 체스 말 종류
     * @param color 생성할 체스 말 색깔
     * @return 신규 채스 말 객체
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
     * 표현식을 받아 새로운 체스말을 생성한다.
     * @param representation 체스말 표현식
     * @return 해당 표현식과 일치하는 체스말 객체
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
