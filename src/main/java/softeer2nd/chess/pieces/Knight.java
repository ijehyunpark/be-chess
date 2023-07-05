package softeer2nd.chess.pieces;

/**
 * 체스의 나이트 객체를 나타낸다.
 */
public class Knight extends Piece {
    private static final Knight BLACK_KNIGHT = new Knight(Color.BLACK);
    private static final Knight WHITE_KNIGHT = new Knight(Color.WHITE);
    private Knight(Color color) {
        super(color, Type.KNIGHT);
    }

    /**
     * 흑색 나이트 싱글톤 인스턴스를 불러온다.
     * @return 흑색 나이트 객체
     */
    public static Knight createBlackKnight() {
        return BLACK_KNIGHT;
    }

    /**
     * 흰색 나이트 싱글톤 인스턴스를 불러온다.
     * @return 흰색 나이트 객체
     */
    public static Knight createWhiteKnight(){
        return WHITE_KNIGHT;
    }
}
