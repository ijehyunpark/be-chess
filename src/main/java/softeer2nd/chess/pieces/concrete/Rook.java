package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.RecursiveMovePiece;

import java.util.List;

import static softeer2nd.chess.pieces.Piece.BasicDirection.*;

public class Rook extends RecursiveMovePiece {
    private static final Rook BLACK_ROOK = new Rook(Color.BLACK);
    private static final Rook WHITE_ROOK = new Rook(Color.WHITE);
    private final List<BasicDirection> moveAble = List.of(
            NORTH,
            EAST,
            SOUTH,
            WEST
    );

    private Rook(Color color) {
        super(color, Type.ROOK);
    }

    /**
     * 흑색 룩 싱글톤 인스턴스를 가져온다.
     */
    public static Rook createBlackRook() {
        return BLACK_ROOK;
    }

    /**
     * 백색 비숍 싱글톤 인스턴스를 가져온다.
     */
    public static Rook createWhiteRook(){
        return WHITE_ROOK;
    }

    @Override
    public List<BasicDirection> getBasicDirection() {
        return moveAble;
    }
}
