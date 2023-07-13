package softeer2nd.chess.pieces;

import softeer2nd.chess.Board.Board;

import java.util.List;

import static softeer2nd.chess.exception.ExceptionMessage.IMPOSSIBLE_MOVEMENT;

public interface MovablePiece extends Piece {
    enum BasicDirection {
        NORTH(-1, 0),
        NORTHEAST(1, -1),
        EAST(0, 1),
        SOUTHEAST(1, 1),
        SOUTH(1, 0),
        SOUTHWEST(-1, 1),
        WEST(0, -1),
        NORTHWEST(-1, -1),

        NNE(1, 2),
        NNW(-1, 2),
        SSE(1, -2),
        SSW(-1, -2),
        EEN(2, 1),
        EES(2, -1),
        WWN(-2, 1),
        WWS(-2, -1);


        private final int yDegree;
        private final int xDegree;

        BasicDirection(int yDegree, int xDegree) {
            this.yDegree = yDegree;
            this.xDegree = xDegree;
        }

        public int getYDegree() {
            return yDegree;
        }

        public int getXDegree() {
            return xDegree;
        }
    }

    class Direction {
        private final int y;
        private final int x;

        public Direction(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    void verifyMove(Board board, Board.Position source, Board.Position destination);

    default void verifyTargetMove(List<Direction> moveAble, Board.Position destination) {
        boolean isMoveAble = moveAble.stream()
                .anyMatch(direction -> destination.getYPos() == direction.getY() &&
                        destination.getXPos() == direction.getX());

        if (!isMoveAble) {
            throw new IllegalArgumentException(IMPOSSIBLE_MOVEMENT);
        }
    }
}
