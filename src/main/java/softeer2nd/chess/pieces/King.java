package softeer2nd.chess.pieces;

/**
 * 체스의 킹 객체를 나타낸다.
 */
public class King extends Piece {
    private static final King BLACK_KING = new King(Color.BLACK);
    private static final King WHITE_KING = new King(Color.WHITE);

    private King(Color color) {
        super(color, Type.KING);
    }

    /**
     * 흑색 퀸 싱글톤 인스턴스를 불러온다.
     * @return 흑색 퀸 객체
     */
    public static King createBlackKing() {
        return BLACK_KING;
    }

    /**
     * 흰색 퀸 싱글톤 인스턴스를 불러온다.
     * @return 흰색 퀸 객체
     */
    public static King createWhiteKing(){
        return WHITE_KING;
    }
}
