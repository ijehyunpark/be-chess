package softeer2nd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    /**
     * 특정 색깔 폰이 생성되는지 검증한다.
     * @param color 검증할 폰 색깔을 나타낸다.
     */
    void verifyPawn(final String color){
        Pawn pawn = new Pawn(color);
        assertThat(pawn.getColor()).isEqualTo(color);
    }

    @Test
    @DisplayName("흰색 및 검은색 폰이 생성되어야 한다")
    void create() {
        verifyPawn("white");
        verifyPawn("black");
    }


}