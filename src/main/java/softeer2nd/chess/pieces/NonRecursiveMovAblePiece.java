package softeer2nd.chess.pieces;

import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.Position;
import softeer2nd.chess.pieces.concrete.King;
import softeer2nd.chess.pieces.concrete.Knight;
import softeer2nd.chess.pieces.concrete.Pawn;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.exception.ExceptionMessage.IMPOSSIBLE_MOVEMENT;

public abstract class NonRecursiveMovAblePiece extends AbstractPiece implements MovablePiece {

    protected NonRecursiveMovAblePiece(Color color, Type type) {
        super(color, type);
    }

    abstract public List<Direction> getDirection();

    /**
     * 단순하게 기물이 한 번 이동 가능한 위치로 확장한다.
     * Note: 한 번 이동되는 {@link Pawn}, {@link Knight}, {@link King} 기물에 한정하여 사용한다.
     *
     * @param moveAble      확장된 기물의 움직임을 저장할 리스트 객체
     * @param directionList 기물의 움직임을 확장할 방향 (Note: 보드판의 범위를 넘어서 확장되지 않는다.
     * @param position      탐색 중에 사용되는 보드판 좌표
     */
    protected void makeMoveAble(Board board, List<Position> moveAble, List<Direction> directionList, Position position) {
        for (Direction direction : directionList) {
            Position nextPosition = movePosition(position, direction);

            if (board.isOutOfBoardIndex(position)) {
                continue;
            }
            moveAble.add(nextPosition);
        }
    }

    @Override
    public void verifyMove(Board board, Position source, Position destination) {
        if (Piece.isSameColor(board.findPiece(source), board.findPiece(destination))) {
            throw new IllegalArgumentException(IMPOSSIBLE_MOVEMENT);
        }

        List<Position> moveAble = new ArrayList<>();
        makeMoveAble(board, moveAble, getDirection(), source);

        verifyTargetMove(moveAble, destination);
    }
}
