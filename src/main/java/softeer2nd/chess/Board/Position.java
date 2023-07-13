package softeer2nd.chess.Board;

import static softeer2nd.chess.Board.Board.COLUMN_NUMBER;

public class Position {
    private final int yPos;
    private final int xPos;

    /**
     * @param position "a3" 과 같은 보드판 위치 정보를 사용해야 합니다. <br/>
     *                 이는 8 * 8 보드판 인덱스에서 (col = 5, row = 0)를 나타냅니다.
     */
    public Position(String position) {
        yPos = COLUMN_NUMBER - Character.getNumericValue(
                position.charAt(1));
        xPos = position.charAt(0) - 'a';
    }

    public Position(int col, int row) {
        this.yPos = col;
        this.xPos = row;
    }

    public int getYPos() {
        return yPos;
    }

    public int getXPos() {
        return xPos;
    }

}
