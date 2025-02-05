package softeer2nd.chess.pieces.concrete;

import softeer2nd.chess.Board.Board;
import softeer2nd.chess.pieces.NonRecursiveMovePiece;
import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.pieces.Piece.BasicDirection.*;

public class Pawn extends NonRecursiveMovePiece {
    private static final Pawn BLACK_PAWN = new Pawn(Color.BLACK);
    private static final Pawn WHITE_PAWN = new Pawn(Color.WHITE);
    private static final List<BasicDirection> moveAble = List.of(
    );
    private static final List<BasicDirection> enPassantMoveAble = List.of(
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
    public static Pawn createWhitePawn(){
        return WHITE_PAWN;
    }

    @Override
    public List<BasicDirection> getBasicDirection() {
        return moveAble;
    }

    private void enPassant(Board board, List<BasicDirection> pawnMoveAble, final int currentY, final int currentX) {
        for(BasicDirection direction : enPassantMoveAble) {
            int nextY = currentY + direction.getYDegree();
            int nextX = currentX + direction.getXDegree();

            if(nextY < 0 || nextY >= Board.COLUMN_NUMBER ||
                    nextX < 0 || nextX >= Board.ROW_NUMBER)
                continue;

            Piece target = board.findPiece(nextY, nextX);
            if(target.getPieceType() != Type.NO_PIECE
                    && target.getColor() != board.findPiece(currentY, currentX).getColor())
                pawnMoveAble.add(direction);
        }
    }
    @Override
    protected void makeMoveAble(Board board, List<Direction> moveAble, List<BasicDirection> directionList, final int currentY, final int currentX) {
        List<BasicDirection> pawnMoveAble = new ArrayList<>(directionList);
        enPassant(board, pawnMoveAble, currentY, currentX);
        super.makeMoveAble(board, moveAble, pawnMoveAble, currentY, currentX);
    }

    @Override
    public void verifyMove(Board board, Board.Position source, Board.Position target) {
        if(board.findPiece(source).getColor() == board.findPiece(target).getColor())
            throw new IllegalArgumentException("같은 편 기물이 존재합니다.");

        List<Direction> moveAble = new ArrayList<>();
        List<BasicDirection> directionList = List.of(
                board.findPiece(source).isBlack() ? SOUTH : NORTH
        );
        makeMoveAble(board, moveAble, directionList, source.getYPos(), source.getXPos());
        verifyTargetMove(target, moveAble);
    }
}
