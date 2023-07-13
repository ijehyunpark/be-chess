package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.pieces.RecursiveMovePiece;

import java.util.List;

import static softeer2nd.chess.pieces.Piece.BasicDirection.*;

public class Bishop extends RecursiveMovePiece {
    private static final Bishop BLACK_BISHOP = new Bishop(Color.BLACK);
    private static final Bishop WHITE_BISHOP = new Bishop(Color.WHITE);
    private final List<BasicDirection> moveAble = List.of(
            NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST
    );

    private Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    /**
     * 흑색 비숍 싱글톤 인스턴스를 가져온다.
     */
    public static Bishop createBlackBishop() {
        return BLACK_BISHOP;
    }

    /**
     * 백색 비숍 싱글톤 인스턴스를 가져온다.
     */
    public static Bishop createWhiteBishop() {
        return WHITE_BISHOP;
    }

    @Override
    public List<BasicDirection> getBasicDirection() {
        return moveAble;
    }
}
