package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PieceTest {

    /**
     * 특정 색깔 폰이 생성되는지 검증한다.
     * @param color 검증할 폰 색깔을 나타낸다.
     */
    void verifyPawn(final Color color){
        Pawn pawn = new Pawn(color);
        assertEquals(color.getName(), pawn.getColor());
        assertEquals(color.getRepresentation(pawn), pawn.getRepresentation());
    }

    @Test
    @DisplayName("흰색 및 검은색 폰이 생성되어야 한다")
    void create() {
        verifyPawn(Color.WHITE);
        verifyPawn(Color.BLACK);
    }

    @Test
    void create_기본생성자() throws Exception {
        Pawn pawn = new Pawn();
        assertEquals(Color.WHITE.getName(), pawn.getColor());
    }


}