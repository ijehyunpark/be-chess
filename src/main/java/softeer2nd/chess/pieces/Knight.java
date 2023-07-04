package softeer2nd.chess.pieces;

import softeer2nd.chess.Color;

/**
 * 체스의 나이트 객체를 나타낸다.
 */
public class Knight extends Piece {
    private final PieceType type = PieceType.KNIGHT;
    public Knight() {
        super();
    }
    public Knight(Color color) {
        super(color);
    }

    @Override
    public PieceType getPieceType() {
        return this.type;
    }
}
