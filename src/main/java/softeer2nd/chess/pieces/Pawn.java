package softeer2nd.chess.pieces;

import softeer2nd.chess.Color;

/**
 * 체스의 폰 객체를 나타낸다.
 */
public class Pawn extends Token {
    public Pawn() {
        super();
    }

    public Pawn(Color color) {
        super(color);
    }

    @Override
    public boolean isPawn() {
        return true;
    }
}
