package softeer2nd.chess.pieces;

import softeer2nd.chess.Color;

/**
 * 체스의 룩 객체를 나타낸다.
 */
public class Rook extends Piece {
    private final PieceType type = PieceType.ROOK;
    public Rook() {
        super();
    }

    public Rook(Color color) {
        super(color);
    }

    @Override
    public PieceType getPieceType() {
        return this.type;
    }


    @Override
    public char getRepresentation() {
        return getColor() == Color.BLACK ? 'R' : 'r';
    }
}
