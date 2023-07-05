package softeer2nd.chess.pieces;

/**
 * 체스의 룩 객체를 나타낸다.
 */
public class Rook extends Piece {
    private static final Rook BLACK_ROOK = new Rook(Color.BLACK);
    private static final Rook WHITE_ROOK = new Rook(Color.WHITE);
    private Rook(Color color) {
        super(color, Type.ROOK);
    }

    /**
     * 흑색 룩 싱글톤 인스턴스를 불러온다.
     * @return 흑색 룩 객체
     */
    public static Rook createBlackRook() {
        return BLACK_ROOK;
    }

    /**
     * 흰색 룩 싱글톤 인스턴스를 불러온다.
     * @return 흰색 룩 객체
     */
    public static Rook createWhiteRook(){
        return WHITE_ROOK;
    }
}
