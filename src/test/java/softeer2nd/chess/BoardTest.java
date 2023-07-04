package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {

    Board board;
    @BeforeEach
    void setUp() {
        board = new Board();
    }

    /**
     * 보드판에 새로운 폰 객체를 추가하는 테스트 단위이다.
     * @param color 추가할 폰 객체 색깔
     */
    void verifyAddPawnInBoard(final Color color) {
        int beforeSize = board.size();

        Pawn pawn = new Pawn(color);
        board.add(pawn);
        assertEquals(beforeSize + 1, board.size());
        assertEquals(pawn, board.findPawn(beforeSize));
    }

    @Test
    @DisplayName("보드판 생성 테스트")
    void create(){
        verifyAddPawnInBoard(Color.WHITE);
        verifyAddPawnInBoard(Color.BLACK);
    }

    @Test
    @DisplayName("잘못된 인덱스 접근 시도 테스트")
    void find(){
        assertThrows(IllegalArgumentException.class, () -> board.findPawn(-1));
        assertThrows(IllegalArgumentException.class, () -> board.findPawn(0));
        verifyAddPawnInBoard(Color.WHITE);
    }

    @Test
    @DisplayName("체스 보드판 초기화 상태를 검증한다.")
    void init() {
        Character[][] initBoard =
                {{'.','.','.','.','.','.','.','.'},
                {'P','P','P','P','P','P','P','P'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'P','P','P','P','P','P','P','P'},
                {'.','.','.','.','.','.','.','.'}};

//        for(int i = 0; i < Board.COLUMN_NUMBER; i++){
//            for(int j = 0; j < Board.ROW_NUMBER; j++){
//                assertEquals(initBoard[i][j], board.getTokenByIndex(i, j).getRepresentation());
//            }
//        }


    }
}