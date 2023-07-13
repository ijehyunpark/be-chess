package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.Position;
import softeer2nd.chess.exception.ExceptionMessage;
import softeer2nd.chess.pieces.NonRecursiveMovablePiece;
import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.pieces.MovablePiece.Direction.*;

public class Pawn extends NonRecursiveMovablePiece {

    private static final Pawn BLACK_PAWN = new Pawn(Color.BLACK);
    private static final Pawn WHITE_PAWN = new Pawn(Color.WHITE);
    private static final List<Direction> movableDirection = List.of(
    );
    private static final List<Direction> attackMovableDirection = List.of(
            NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST
    );

    private Pawn(Color color) {
        super(color, Type.PAWN);
    }

    /**
     * 흑색 폰 싱글톤 인스턴스를 가져온다.
     */
    public static Pawn createBlackPawn() {
        return BLACK_PAWN;
    }

    /**
     * 백색 폰 싱글톤 인스턴스를 가져온다.
     */
    public static Pawn createWhitePawn() {
        return WHITE_PAWN;
    }

    @Override
    public List<Direction> getDirection() {
        return movableDirection;
    }

    private void attackMove(Board board, List<Direction> pawnMoveAble, Position position) {
        for (Direction direction : attackMovableDirection) {
            Position nextPosition = movePosition(position, direction);

            if (board.isOutOfBoardIndex(nextPosition)) {
                continue;
            }

            Piece target = board.findPiece(nextPosition);
            if (target.getPieceType() != Type.NO_PIECE
                    && target.getColor() != board.findPiece(position).getColor()) {
                pawnMoveAble.add(direction);
            }
        }
    }

    @Override
    protected void makeMoveAble(Board board, List<Position> moveAble, List<Direction> directionList, Position position) {
        List<Direction> pawnMoveAble = new ArrayList<>(directionList);
        attackMove(board, pawnMoveAble, position);
        super.makeMoveAble(board, moveAble, pawnMoveAble, position);
    }

    @Override
    public void verifyMove(Board board, Position source, Position destination) {
        if (Piece.isSameColor(this, board.findPiece(destination))) {
            throw new IllegalArgumentException(ExceptionMessage.IMPOSSIBLE_MOVEMENT);
        }

        List<Direction> directionList = new ArrayList<>();

        // 색깔에 따른 직진 전진 방향 구분
        basicStraightMove(board, source, destination, directionList);

        // 처음 움직일 경우 두칸 전진
        moveDoubleStraightMove(board, source, destination, directionList);

        List<Position> moveAble = new ArrayList<>();
        makeMoveAble(board, moveAble, directionList, source);
        verifyDestinationMove(moveAble, destination);
    }

    private void moveDoubleStraightMove(Board board, Position source, Position destination, List<Direction> directionList) {
        if(!board.isMoved(source)){
            Direction doubleStraight = this.isBlack() ? SOUTH2 : NORTH2;
            verifyForwardDifferentColor(board, source, destination, doubleStraight);
            directionList.add(doubleStraight);
        }
    }

    private void basicStraightMove(Board board, Position source, Position destination, List<Direction> directionList) {
        Direction straight = this.isBlack() ? SOUTH : NORTH;
        verifyForwardDifferentColor(board, source, destination, straight);
        directionList.add(straight);
    }

    public void verifyForwardDifferentColor(Board board, Position source, Position destination, Direction forward) {
        if (!board.findPiece(destination).isBlank() &&
                Position.isSamePosition(movePosition(source, forward), destination)) {
            throw new IllegalArgumentException(ExceptionMessage.IMPOSSIBLE_MOVEMENT);
        }
    }

}
