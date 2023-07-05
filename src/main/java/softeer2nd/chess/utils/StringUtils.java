package softeer2nd.chess.utils;

public class StringUtils {

    public static final String NEWLINE = System.getProperty("line.separator");

    private StringUtils() {

    }

    /**
     * 문자열 끝에 개행 문자를 추가한다.
     * @param string 기존 문자열
     * @return 기존 문자열에 개행 문자열을 추가한 문자열
     */
    public static String appendNewLine(final String string) {
        return string + NEWLINE;
    }

    /**
     * 문자열 끝에 개행 문자를 추가한다.
     * @param builder 기존 문자열을 만드는 builder
     * @return 기존 문자열 builder에 개행 문자열을 추가한 문자열 builder
     */
    public static StringBuilder appendNewLine(final StringBuilder builder) {
        return builder.append(NEWLINE);
    }
}
