package softeer2nd;

/**
 * 체스의 폰 객체를 나타낸다.
 */
public class Pawn {
    private String color;
    public static final String WHITE = "white";
    public static final String BLACK = "black";

    public Pawn() {
        this.color = WHITE;
    }
    public Pawn(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }
}
