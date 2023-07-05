package softeer2nd.chess.pieces;

import java.lang.reflect.Type;

/**
 * 체스의 폰 객체를 나타낸다.
 */
public class Pawn extends Piece {
    private static final Pawn BLACK_PAWN = new Pawn(Color.BLACK);
    private static final Pawn WHITE_PAWN = new Pawn(Color.WHITE);
    private Pawn(Color color) {
        super(color, Type.PAWN);
    }

    /**
     * 흑색 폰 싱글톤 인스턴스를 불러온다.
     * @return 흑색 폰 객체
     */
    public static Pawn createBlackPawn() {
        return BLACK_PAWN;
    }

    /**
     * 흰색 폰 싱글톤 인스턴스를 불러온다.
     * @return 흰색 폰 객체
     */
    public static Pawn createWhitePawn(){
        return WHITE_PAWN;
    }
}
