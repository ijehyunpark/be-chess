package softeer2nd.chess.pieces;

/**
 * 보드판의 빈 칸을 표현하기 위해 사용된다.
 */
public class BlankPiece extends AbstractPiece {

    private static final BlankPiece BLANK_PIECE_INSTANCE = new BlankPiece();

    private BlankPiece() {
        super(Color.NO_COLOR, Type.NO_PIECE);
    }

    /**
     * 원시적인 기물의 싱글톤 인스턴스를 가져온다.
     */
    public static BlankPiece createBlank() {
        return BlankPiece.BLANK_PIECE_INSTANCE;
    }

}
