package softeer2nd.chess.pieces;

public class Queen extends Piece {
    private static final Queen BLACK_QUEEN = new Queen(Color.BLACK);
    private static final Queen WHITE_QUEEN = new Queen(Color.WHITE);
    private Queen(Color color) {
        super(color, Type.QUEEN);
    }

    /**
     * 흑색 퀸 싱글톤 인스턴스를 가져온다.
     */
    public static Queen createBlackQueen() {
        return BLACK_QUEEN;
    }

    /**
     * 백색 비숍 싱글톤 인스턴스를 가져온다.
     */
    public static Queen createWhiteQueen(){
        return WHITE_QUEEN;
    }
}
