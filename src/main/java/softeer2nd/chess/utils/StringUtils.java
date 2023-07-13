package softeer2nd.chess.utils;

public abstract class StringUtils {

    public static final String NEW_LINE = System.getProperty("line.separator");

    public static String appendNewLine(final String message) {
        return message + NEW_LINE;
    }

    public static String appendNewLine(final String... messages) {
        return appendNewLine(String.join(NEW_LINE, messages));
    }

    public static StringBuilder appendNewLine(final StringBuilder builder) {
        return builder.append(NEW_LINE);
    }

}
