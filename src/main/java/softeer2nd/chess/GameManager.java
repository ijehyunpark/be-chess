package softeer2nd.chess;

import softeer2nd.chess.Board.Board;
import softeer2nd.chess.Board.Position;
import softeer2nd.chess.exception.ExceptionMessage;
import softeer2nd.chess.pieces.BlankPiece;
import softeer2nd.chess.pieces.MovablePiece;
import softeer2nd.chess.pieces.Piece;

import static softeer2nd.chess.exception.ExceptionMessage.OUT_OF_BOUND_INPUT;

public abstract class GameManager {
    /**
     * 보드판에서 한 기물을 이동시킨다. <br/>
     * 구체적으로 가능한 이동일 경우 보드판에서 sourcePosition와 targetPosition의 위치를 교환한다.
     *
     * @throws IllegalArgumentException 범위를 벗어나는 이동, 같은 색깔 기물로 이동, 빈 칸 이동의 경우 발생한다.
     */
    public static void move(Board board, Position sourcePosition, Position destinationPosition) {
        verifyOutOfBoardIndex(destinationPosition);

        MovablePiece sourcePiece = ifNotBlankCastMovable(board.findPiece(sourcePosition));
        Piece destinationPiece = board.findPiece(destinationPosition);

        sourcePiece.verifyMove(board, sourcePosition, destinationPosition);

        // 다른 색 기물일 경우 제거한다.
        if (!Piece.isSameColorAndHasPiece(sourcePiece, destinationPiece)) {
            destinationPiece = BlankPiece.createBlank();
        }

        board.assignPiece(sourcePosition, destinationPiece);
        board.assignPiece(destinationPosition, sourcePiece);
    }

    private static MovablePiece ifNotBlankCastMovable(Piece sourcePiece) {
        if (sourcePiece.isBlank()) {
            throw new IllegalArgumentException(ExceptionMessage.MOVE_NOT_MOVABLE_PIECE);
        }
        return (MovablePiece) sourcePiece;
    }

    private static void verifyOutOfBoardIndex(Position destinationPosition) {
        if (destinationPosition.getYPos() < 0 || destinationPosition.getYPos() >= Board.COLUMN_NUMBER ||
                destinationPosition.getXPos() < 0 || destinationPosition.getXPos() >= Board.ROW_NUMBER) {
            throw new IllegalArgumentException(OUT_OF_BOUND_INPUT);
        }
    }
}
