package softeer2nd.chess.pieces;

import softeer2nd.chess.Color;

public abstract class Token {
    private final Color color;

    public Token() {
        this.color = Color.WHITE;
    }
    public Token(Color color){
        this.color = color;
    }

    public String getColor(){
        return this.color.getName();
    }

    public char getRepresentation() {
        return this.color.getRepresentation(this);
    }

    public boolean isPawn() {
        return false;
    }
}
