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


    public String getColor(){
        return this.color.getName();
    }

    public char getRepresentation() {
        return this.color.getRepresentation(getPieceType());
    }
    public abstract PieceType getPieceType();
}
