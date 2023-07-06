package softeer2nd.chess.pieces;

import java.util.List;

import static softeer2nd.chess.pieces.Piece.BasicDirection.*;

public class Queen extends RecursiveMovePiece {
    private static final Queen BLACK_QUEEN = new Queen(Color.BLACK);
    private static final Queen WHITE_QUEEN = new Queen(Color.WHITE);
    private final List<BasicDirection> moveAble = List.of(
            NORTH, NORTHEAST, EAST, SOUTHEAST,
            SOUTH, SOUTHWEST, WEST, NORTHWEST
    );

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

    @Override
    public List<BasicDirection> getBasicDirection() {
        return moveAble;
    }
}
