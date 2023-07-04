package softeer2nd.chess.pieces;

import softeer2nd.chess.Color;

public abstract class Piece {
    private final Color color;

    public Piece() {
        this.color = Color.WHITE;
    }
    public Piece(Color color){
        this.color = color;
    }

    public String getColor(){
        return this.color.getName();
    }

    public char getRepresentation() {
        return this.color.getRepresentation(this);
    }

    public abstract PieceType getPieceType();
}