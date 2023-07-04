package softeer2nd.chess;

import softeer2nd.chess.pieces.Token;

public enum Color {
    BLACK("black", 'P'),
    WHITE("white", 'p');

    private final String name;
    private final char pawnRepresentation;
    Color(String name, char pawnRepresentation) {
        this.name = name;
        this.pawnRepresentation = pawnRepresentation;
    }

    public char getRepresentation(Token token){
        if(token.isPawn()){
            return this.pawnRepresentation;
        }
        throw new IllegalArgumentException("정의되지 않은 게임 말입니다.");
    }

    public String getName(){
        return this.name;
    }
}
