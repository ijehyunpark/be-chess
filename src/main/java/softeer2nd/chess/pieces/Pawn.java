package softeer2nd.chess.pieces;

/**
 * 체스의 폰 객체를 나타낸다.
 */
public class Pawn {
    private String color;
    public static final String WHITE_COLOR = "white";
    public static final String BLACK_COLOR = "black";

    public Pawn() {
        this.color = WHITE_COLOR;
    }
    public Pawn(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
