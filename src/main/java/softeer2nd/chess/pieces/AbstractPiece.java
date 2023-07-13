package softeer2nd.chess.pieces;

public abstract class AbstractPiece implements Piece {
    private final Color color;
    private final Type type;

    protected AbstractPiece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return this.color;
    }

    public Type getPieceType() {
        return this.type;
    }

    public char getRepresentation() {
        return color == Piece.Color.BLACK ?
                type.getBlackRepresentation() : type.getWhiteRepresentation();
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }

    public boolean isWhite() {
        return color == Color.WHITE;
    }

    public boolean isBlank() {
        return color == Color.NO_COLOR;
    }

    public boolean isSameColor(Piece piece1, Piece piece2) {
        return piece1.getColor() == piece2.getColor();
    }
}
