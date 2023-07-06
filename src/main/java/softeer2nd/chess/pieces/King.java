package softeer2nd.chess.pieces;

import java.util.List;

import static softeer2nd.chess.pieces.Piece.BasicDirection.*;

public class King extends NonRecursiveMovePiece {
    private static final King BLACK_KING = new King(Color.BLACK);
    private static final King WHITE_KING = new King(Color.WHITE);
    private final List<BasicDirection> moveAble =
            List.of(NORTH, SOUTH, EAST, WEST);

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

    @Override
    public List<BasicDirection> getBasicDirection() {
        return moveAble;
    }
}
