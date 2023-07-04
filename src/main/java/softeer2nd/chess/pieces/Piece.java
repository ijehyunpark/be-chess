package softeer2nd.chess.pieces;

import softeer2nd.chess.Color;

/**
 * 채스의 게임 말의 공통적인 구현체 및 생성 팩토리 메소드를 가지고 있다.
 */
public abstract class Piece {
    private final Color color;

    protected Piece() {
        this.color = Color.WHITE;
    }
    protected Piece(Color color){
        this.color = color;
    }

    /**
     * 새로운 폰을 생성한다.
     * @param color 해당 게임 말 객체의 색깔
     * @return 신규 폰 객체
     */
    public static Piece createPawn(Color color) {
        return new Pawn(color);
    }

    /**
     * 새로운 나이트를 생성한다.
     * @param color 해당 게임 말 객체의 색깔
     * @return 신규 기사 객체
     */
    public static Piece createKnight(Color color) {
        return new Knight(color);
    }

    /**
     * 새로운 비숍를 생성한다.
     * @param color 해당 게임 말 객체의 색깔
     * @return 신규 비숍 객체
     */
    public static Piece createBishop(Color color) {
        return new Bishop(color);
    }


    /**
     * 새로운 룩를 생성한다.
     * @param color 해당 게임 말 객체의 색깔
     * @return 신규 룩 객체
     */
    public static Piece createRook(Color color) {
        return new Rook(color);
    }


    /**
     * 새로운 퀸를 생성한다.
     * @param color 해당 게임 말 객체의 색깔
     * @return 신규 퀸 객체
     */
    public static Piece createQueen(Color color) {
        return new Queen(color);
    }


    /**
     * 새로운 킹를 생성한다.
     * @param color 해당 게임 말 객체의 색깔
     * @return 신규 킹 객체
     */
    public static Piece createKing(Color color) {
        return new King(color);
    }

    /**
     * 새로운 체스 말을 생성한다.
     * @param type 생성할 체스 말 종류
     * @param color 생성할 체스 말 색깔
     * @return 신규 채스 말 객체
     */
    public static Piece createPiece(PieceType type, Color color) {
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
    public abstract PieceType getPieceType();

    public abstract char getRepresentation();
}
