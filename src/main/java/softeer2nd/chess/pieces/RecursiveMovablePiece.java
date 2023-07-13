package softeer2nd.chess.pieces;

import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.Position;
import softeer2nd.chess.pieces.concrete.Bishop;
import softeer2nd.chess.pieces.concrete.Queen;
import softeer2nd.chess.pieces.concrete.Rook;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.exception.ExceptionMessage.IMPOSSIBLE_MOVEMENT;

public abstract class RecursiveMovablePiece extends AbstractPiece implements MovablePiece {

    protected RecursiveMovablePiece(Color color, Type type) {
        super(color, type);
    }

    abstract public List<Direction> getDirection();

    /**
     * 재귀적으로 기물의 이동 가능 움직임을 확장한다. <br />
     * Note: 한 줄 내에서 자유롭게 이동 가능한 {@link Rook}, {@link Bishop}, {@link Queen} 기물에 한정하여 사용한다.
     *
     * @param moveAble  확장 가능한 움직임을 재귀적으로 탐색하면서 {@link Piece.Type#NO_PIECE} 가 아닌 경우를 만나면 탐색을 중단하고 해당 Piece의 위치를 포함하여 탐색했던 위치를 리스트에 담는다.
     * @param direction 기물의 움직임을 확장할 방향 (Note: 보드판의 범위를 넘어서 확장되지 않는다.
     * @param current  탐색 중에 사용되는 보드판 좌표
     */
    protected void expandPieceMoveAble(Board board, List<Position> moveAble, Direction direction, Position current) {
        current = movePosition(current, direction);
        if (board.isOutOfBoardIndex(current)) {
            return;
        }

        moveAble.add(current);

        if (board.isBlankPiece(current)) {
            expandPieceMoveAble(board, moveAble, direction, current);
        }
    }

    @Override
    public void verifyMove(Board board, Position source, Position destination) {
        if (this.getColor() == board.findPiece(destination).getColor()) {
            throw new IllegalArgumentException(IMPOSSIBLE_MOVEMENT);
        }

        List<Position> moveAble = new ArrayList<>();
        getDirection().forEach(direction -> expandPieceMoveAble(board, moveAble, direction, source));

        verifyDestinationMove(moveAble, destination);
    }

}
