package softeer2nd.chess.Board;

import softeer2nd.chess.pieces.Piece;

import static softeer2nd.chess.Board.Board.COLUMN_NUMBER;
import static softeer2nd.chess.Board.Board.ROW_NUMBER;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public abstract class BoardView {
    /**
     * 현재 보드판의 상태를 문자열로 변환하여 반환한다.
     */
    public static String showBoard(Board board) {
        StringBuilder builder = new StringBuilder();
        for (int col = 0; col < COLUMN_NUMBER; col++) {
            for (int row = 0; row < ROW_NUMBER; row++) {
                Piece piece = board.findPiece(col, row);
                builder.append(piece.getRepresentation());
            }
            appendNewLine(builder);
        }
        return builder.toString();
    }
}
