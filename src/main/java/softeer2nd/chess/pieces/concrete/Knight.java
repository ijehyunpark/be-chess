package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.NonRecursiveMovablePiece;

import java.util.List;

import static softeer2nd.chess.pieces.MovablePiece.Direction.*;

public class Knight extends NonRecursiveMovablePiece {

    private static final Knight BLACK_KNIGHT = new Knight(Color.BLACK);
    private static final Knight WHITE_KNIGHT = new Knight(Color.WHITE);
    private static final List<Direction> movableDirection = List.of(
            NNE, NNW, SSE, SSW,
            EEN, EES, WWN, WWS
    );

    private Knight(Color color) {
        super(color, Type.KNIGHT);
    }

    /**
     * 흑색 나이트 싱글톤 인스턴스를 가져온다.
     */
    public static Knight createBlackKnight() {
        return BLACK_KNIGHT;
    }

    /**
     * 백색 나이트싱글톤 인스턴스를 가져온다.
     */
    public static Knight createWhiteKnight() {
        return WHITE_KNIGHT;
    }

    @Override
    public List<Direction> getDirection() {
        return movableDirection;
    }

}
