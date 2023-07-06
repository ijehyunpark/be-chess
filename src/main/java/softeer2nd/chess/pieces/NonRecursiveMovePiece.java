package softeer2nd.chess.pieces;

import softeer2nd.chess.Board.Board;

import java.util.ArrayList;
import java.util.List;

public abstract class NonRecursiveMovePiece extends abstrctPiece {

    protected NonRecursiveMovePiece(Color color, Type type) {
        super(color, type);
    }

    abstract public List<BasicDirection> getBasicDirection();

    /**
     * 단순하게 기물이 한 번 이동 가능한 위치로 확장한다.
     * Note: 한 번 이동되는 {@link Pawn}, {@link Knight}, {@link King} 기물에 한정하여 사용한다.
     * @param moveAble 확장된 기물의 움직임을 저장할 리스트 객체
     * @param directionList 기물의 움직임을 확장할 방향 (Note: 보드판의 범위를 넘어서 확장되지 않는다.
     * @param currentY 탐색 중에 사용되는 y 좌표
     * @param currentX 탐색 중에 사용되는 x 좌표
     */
    protected void makeKingMoveAble(final List<Direction> moveAble, final List<BasicDirection> directionList, final int currentY, final int currentX) {
        for (BasicDirection direction : directionList) {
            int nextY = currentY + direction.getYDegree();
            int nextX = currentX + direction.getXDegree();

            if(nextY < 0 || nextY >= Board.COLUMN_NUMBER ||
                    nextX < 0 || nextX >= Board.ROW_NUMBER)
                continue;
            moveAble.add(new Direction(nextY, nextX));
        }
    }

    @Override
    public void verifyMove(Board.Position source, Board.Position target) {
        List<Direction> moveAble = new ArrayList<>();
        makeKingMoveAble(moveAble, getBasicDirection(), source.getYPos(), source.getXPos());
        verifyTargetMove(target, moveAble);
    }
}
