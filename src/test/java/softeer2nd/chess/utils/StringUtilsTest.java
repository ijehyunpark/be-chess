package softeer2nd.chess.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.utils.StringUtils.NEWLINE;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class StringUtilsTest {

    @Test
    @DisplayName("개행 문자 추가 테스트")
    void testAppendNewLine() {
        String origin = "sample";

        String result = appendNewLine(origin);
        assertEquals(origin + NEWLINE, result);

        StringBuilder builder = new StringBuilder();
        builder.append(origin);
        appendNewLine(builder);
        assertEquals(origin + NEWLINE, builder.toString());

    }

}