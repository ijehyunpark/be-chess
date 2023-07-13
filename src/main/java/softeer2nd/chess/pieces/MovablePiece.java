package softeer2nd.chess.pieces;

import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.Position;

import java.util.List;

import static softeer2nd.chess.exception.ExceptionMessage.IMPOSSIBLE_MOVEMENT;

public interface MovablePiece extends Piece {

    enum Direction {

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
        WWS(-2, -1),

        NORTH2(-2, 0),
        SOUTH2(2, 0);


        private final int columnDegree;
        private final int rowDegree;

        Direction(int columnDegree, int rowDegree) {
            this.columnDegree = columnDegree;
            this.rowDegree = rowDegree;
        }

        public int getColumnDegree() {
            return columnDegree;
        }

        public int getRowDegree() {
            return rowDegree;
        }

    }

    void verifyMove(Board board, Position source, Position destination);

    default void verifyDestinationMove(List<Position> moveAble, Position destination) {
        boolean isMoveAble = moveAble.stream()
                .anyMatch(movePosition -> Position.isSamePosition(movePosition, destination));

        if (!isMoveAble) {
            throw new IllegalArgumentException(IMPOSSIBLE_MOVEMENT);
        }
    }

    default Position movePosition(Position position, Direction direction) {
        return new Position(position.getColumn() + direction.getColumnDegree(), position.getRow() + direction.getRowDegree());
    }

}
