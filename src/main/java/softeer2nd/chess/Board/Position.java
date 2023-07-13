package softeer2nd.chess.Board;

import static softeer2nd.chess.Board.Board.COLUMN_NUMBER;

public class Position {

    private final int column;
    private final int row;

    /**
     * @param position "a3" 과 같은 보드판 위치 정보를 사용해야 합니다. <br/>
     *                 이는 8 * 8 보드판 인덱스에서 (col = 5, row = 0)를 나타냅니다.
     */
    public Position(String position) {
        column = COLUMN_NUMBER - Character.getNumericValue(
                position.charAt(1));
        row = position.charAt(0) - 'a';
    }

    public Position(int col, int row) {
        this.column = col;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public static boolean isSamePosition(Position position1, Position position2) {
        return position1.getColumn() == position2.getColumn() &&
                position1.getRow() == position2.getRow();
    }

}
