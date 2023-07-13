package softeer2nd.chess.utils;

public abstract class StringUtils {

    public static final String NEWLINE = System.getProperty("line.separator");

    public static String appendNewLine(final String message){
        return message + NEWLINE;
    }

    public static String appendNewLine(final String... messages) {
        return appendNewLine(String.join(NEWLINE, messages));
    }

    public static StringBuilder appendNewLine(final StringBuilder builder) {
        return builder.append(NEWLINE);
    }
}
