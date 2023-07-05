package softeer2nd.chess.pieces;

public class King extends Piece {
    private static final King BLACK_KING = new King(Color.BLACK);
    private static final King WHITE_KING = new King(Color.WHITE);

    private King(Color color) {
        super(color, Type.KING);
    }

    /**
     * 흑색 킹 싱글톤 인스턴스를 가져온다.
     */
    public static King createBlackKing() {
        return BLACK_KING;
    }

    /**
     * 백색 비숍 싱글톤 인스턴스를 가져온다.
     */
    public static King createWhiteKing(){
        return WHITE_KING;
    }
}
