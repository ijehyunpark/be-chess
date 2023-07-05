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
        KING('k'), QUEEN('q'), ROOK('r'), BISHOP('b'),
        KNIGHT('n'), PAWN('p'), NO_PIECE('.');

        private char representation;

        Type(char representation) {
            this.representation = representation;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }
    }

    private final Color color;
    private final Type type;

    protected Piece() {
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
        return new Piece();
    }

    /**
     * 새로운 폰을 생성한다.
     * @param color 해당 게임 말 객체의 색깔
     * @return 신규 폰 객체
     */
    public static Pawn createPawn(final Color color) {
        return new Pawn(color);
    }

    /**
     * 새로운 나이트를 생성한다.
     * @param color 해당 게임 말 객체의 색깔
     * @return 신규 기사 객체
     */
    public static Knight createKnight(final Color color) {
        return new Knight(color);
    }

    /**
     * 새로운 비숍를 생성한다.
     * @param color 해당 게임 말 객체의 색깔
     * @return 신규 비숍 객체
     */
    public static Bishop createBishop(final Color color) {
        return new Bishop(color);
    }


    /**
     * 새로운 룩를 생성한다.
     * @param color 해당 게임 말 객체의 색깔
     * @return 신규 룩 객체
     */
    public static Rook createRook(final Color color) {
        return new Rook(color);
    }


    /**
     * 새로운 퀸를 생성한다.
     * @param color 해당 게임 말 객체의 색깔
     * @return 신규 퀸 객체
     */
    public static Queen createQueen(final Color color) {
        return new Queen(color);
    }


    /**
     * 새로운 킹를 생성한다.
     * @param color 해당 게임 말 객체의 색깔
     * @return 신규 킹 객체
     */
    public static King createKing(final Color color) {
        return new King(color);
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
                return createPawn(color);
            case KNIGHT:
                return createKnight(color);
            case BISHOP:
                return createBishop(color);
            case ROOK:
                return createRook(color);
            case QUEEN:
                return createQueen(color);
            case KING:
                return createKing(color);
            default:
                throw new IllegalArgumentException("타입이 허용되지 않습니다.");
        }
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
