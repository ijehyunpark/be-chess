package softeer2nd.chess.pieces;

public class Bishop extends Piece {
    private static final Bishop BLACK_BISHOP = new Bishop(Color.BLACK);
    private static final Bishop WHITE_BISHOP = new Bishop(Color.WHITE);
    private Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    /**
     * 흑색 비숍 싱글톤 인스턴스를 가져온다.
     */
    public static Bishop createBlackBishop() {
        return BLACK_BISHOP;
    }

    /**
     * 백색 비숍 싱글톤 인스턴스를 가져온다.
     */
    public static Bishop createWhiteBishop(){
        return WHITE_BISHOP;
    }
}
