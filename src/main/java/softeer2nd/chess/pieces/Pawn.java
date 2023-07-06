package softeer2nd.chess.pieces;

import java.util.List;

import static softeer2nd.chess.pieces.Piece.BasicDirection.*;
import static softeer2nd.chess.pieces.Piece.BasicDirection.WEST;

public class Pawn extends NonRecursiveMovePiece {
    private static final Pawn BLACK_PAWN = new Pawn(Color.BLACK);
    private static final Pawn WHITE_PAWN = new Pawn(Color.WHITE);
    private static final List<BasicDirection> moveAble = List.of(
            NORTH, EAST, WEST
    );
    private Pawn(Color color) {
        super(color, Type.PAWN);
    }

    /**
     * 흑색 폰 싱글톤 인스턴스를 가져온다.
     */
    public static Pawn createBlackPawn() {
        return BLACK_PAWN;
    }

    /**
     * 백색 폰 싱글톤 인스턴스를 가져온다.
     */
    public static Pawn createWhitePawn(){
        return WHITE_PAWN;
    }

    @Override
    public List<BasicDirection> getBasicDirection() {
        return moveAble;
    }
}
