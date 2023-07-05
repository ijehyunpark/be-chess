package softeer2nd.chess.utils;

public class StringUtils {

    public static final String NEWLINE = System.getProperty("line.separator");

    private StringUtils() {

    }

    /**
     * 문자열 끝에 개행 문자를 추가한다.
     */
    public static String appendNewLine(final String string) {
        return string + NEWLINE;
    }

    /**
     * 문자열 끝에 개행 문자를 추가한다.
     */
    public static StringBuilder appendNewLine(final StringBuilder builder) {
        return builder.append(NEWLINE);
    }
}
