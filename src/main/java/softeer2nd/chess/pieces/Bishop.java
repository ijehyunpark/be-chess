package softeer2nd.chess.pieces;

/**
 * 체스의 비숍 객체를 나타낸다.
 */
public class Bishop extends Piece {
    private static final Bishop BLACK_BISHOP = new Bishop(Color.BLACK);
    private static final Bishop WHITE_BISHOP = new Bishop(Color.WHITE);
    private Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    /**
     * 흑색 비숍 싱글톤 인스턴스를 불러온다.
     * @return 흑색 비숍 객체
     */
    public static Bishop createBlackBishop() {
        return BLACK_BISHOP;
    }

    /**
     * 흰색 비숍 싱글톤 인스턴스를 불러온다.
     * @return 흰색 비숍 객체
     */
    public static Bishop createWhiteBishop(){
        return WHITE_BISHOP;
    }
}
