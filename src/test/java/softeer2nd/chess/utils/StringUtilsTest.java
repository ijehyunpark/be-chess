package softeer2nd.chess.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    @DisplayName("개행 문자 추가 테스트")
    void testAppendNewLine() {
        String origin = "sample";

        String result = StringUtils.appendNewLine(origin);
        assertEquals(origin + StringUtils.NEWLINE, result);

        StringBuilder builder = new StringBuilder();
        builder.append(origin);
        StringUtils.appendNewLine(builder);
        assertEquals(origin + StringUtils.NEWLINE, builder.toString());

    }

}