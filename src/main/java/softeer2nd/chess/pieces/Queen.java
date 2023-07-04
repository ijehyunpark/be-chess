package softeer2nd.chess.pieces;

import softeer2nd.chess.Color;

/**
 * 체스의 퀸 객체를 나타낸다.
 */
public class Queen extends Piece {
    private final PieceType type = PieceType.QUEEN;
    public Queen() {
        super();
    }

    public Queen(Color color) {
        super(color);
    }

    @Override
    public PieceType getPieceType() {
        return this.type;
    }


    @Override
    public char getRepresentation() {
        return getColor() == Color.BLACK ? 'Q' : 'q';
    }
}
