package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Pawn;
import softeer2nd.chess.pieces.Piece;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.clear();
    }

    /**
     * 보드판에 새로운 폰 객체를 추가하는 테스트 단위이다.
     * @param color 추가할 폰 객체 색깔
     * @param column 추가할 폰 객체 세로축 위치
     * @param row 추가할 폰 객체 가로축 위치
     */
    void verifyAddPawnInBoard(final Color color, final int column, final int row) {
        int beforeSize = board.size();

        Pawn pawn = new Pawn(color);
        board.add(pawn, column, row);
        assertEquals(beforeSize + 1, board.size());
        assertEquals(pawn, board.findPiece(beforeSize));
    }

    @Test
    @DisplayName("보드판 생성 테스트")
    void create(){
        verifyAddPawnInBoard(Color.WHITE, 0, 0);
        verifyAddPawnInBoard(Color.BLACK, 0, 1);
        assertThrows(IllegalArgumentException.class, () -> verifyAddPawnInBoard(Color.BLACK, 0, 1));
    }

    @Test
    @DisplayName("잘못된 인덱스 접근 시도 테스트")
    void find(){
        assertThrows(IllegalArgumentException.class, () -> board.findPiece(-1));
        assertThrows(IllegalArgumentException.class, () -> board.findPiece(0));
        verifyAddPawnInBoard(Color.WHITE, 0, 0);
    }

    @Test
    @DisplayName("체스 보드판 초기화 상태를 검증한다.")
    void init() {
        char[][] initBoard =
                {{'.','.','.','.','.','.','.','.'},
                {'P','P','P','P','P','P','P','P'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'.','.','.','.','.','.','.','.'},
                {'p','p','p','p','p','p','p','p'},
                {'.','.','.','.','.','.','.','.'}};

        board.initialize();

        for(int i = 0; i < Board.COLUMN_NUMBER; i++){
            for(int j = 0; j < Board.ROW_NUMBER; j++){
                Optional<Piece> piece = board.getPieceByPosition(i,j);
                if(initBoard[i][j] == '.') {
                    assertTrue(piece.isEmpty());
                }
                else {
                    assertTrue(piece.isPresent());
                    assertEquals(initBoard[i][j], piece.get().getRepresentation());
                }
            }
        }
    }

    @Test
    @DisplayName("체스 보드판 문자열 변환이 성공적인지 검증한다.")
    void print() {
        String result = "........\n" +
                "PPPPPPPP\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "........\n" +
                "pppppppp\n" +
                "........\n";

        board.initialize();

        assertEquals(result, board.print());
    }
}