package softeer2nd.chess.pieces;

import softeer2nd.chess.Color;

/**
 * 체스의 비숍 객체를 나타낸다.
 */
public class Bishop extends Piece {
    private final PieceType type = PieceType.BISHOP;
    public Bishop() {
        super();
    }

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public PieceType getPieceType() {
        return this.type;
    }
}
