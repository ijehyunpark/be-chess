package softeer2nd.chess.pieces;

import softeer2nd.chess.Board.Board;

public class King extends Piece {
    private static final King BLACK_KING = new King(Color.BLACK);
    private static final King WHITE_KING = new King(Color.WHITE);
    private static final int[][] MOVE_ABLE_KING = {{0,1},{0,-1},{1,0},{1,1}};

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

    public void move(Board board, Board.Position source, Board.Position target) {
        verifyTargetMove(board, source, target, MOVE_ABLE_KING);
    }
}
