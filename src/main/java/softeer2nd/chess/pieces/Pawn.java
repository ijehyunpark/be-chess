package softeer2nd.chess.pieces;

import java.lang.reflect.Type;

/**
 * 체스의 폰 객체를 나타낸다.
 */
public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, Type.PAWN);
    }
}
