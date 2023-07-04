package softeer2nd.chess.pieces;

import softeer2nd.chess.Color;

/**
 * 체스의 킹 객체를 나타낸다.
 */
public class King extends Piece {
    private final PieceType type = PieceType.KING;
    public King() {
        super();
    }

    public King(Color color) {
        super(color);
    }

    @Override
    public PieceType getPieceType() {
        return this.type;
    }


    @Override
    public char getRepresentation() {
        return getColor() == Color.BLACK ? 'K' : 'k';
    }
}
