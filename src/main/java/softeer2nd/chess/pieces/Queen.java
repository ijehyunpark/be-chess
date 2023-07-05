package softeer2nd.chess.pieces;

/**
 * 체스의 퀸 객체를 나타낸다.
 */
public class Queen extends Piece {
    private static final Queen BLACK_QUEEN = new Queen(Color.BLACK);
    private static final Queen WHITE_QUEEN = new Queen(Color.WHITE);
    private Queen(Color color) {
        super(color, Type.QUEEN);
    }

    /**
     * 흑색 퀸 싱글톤 인스턴스를 불러온다.
     * @return 흑색 퀸 객체
     */
    public static Queen createBlackQueen() {
        return BLACK_QUEEN;
    }

    /**
     * 흰색 퀸 싱글톤 인스턴스를 불러온다.
     * @return 흰색 퀸 객체
     */
    public static Queen createWhiteQueen(){
        return WHITE_QUEEN;
    }
}
