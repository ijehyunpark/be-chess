package softeer2nd.chess;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void verifyAddPawnInBoard(final String color) {
        int beforeSize = board.size();

        Pawn pawn = new Pawn(color);
        board.add(pawn);
        assertEquals(beforeSize + 1, board.size());
        assertEquals(pawn, board.findPawn(beforeSize));
    }

    @Test
    @DisplayName("보드판 생성 테스트")
    void create(){
        verifyAddPawnInBoard(Pawn.WHITE_COLOR);
        verifyAddPawnInBoard(Pawn.BLACK_COLOR);
    }
}