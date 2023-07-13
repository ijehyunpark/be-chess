package softeer2nd.chess.pieces;

public interface Piece {
    enum Color {
        BLACK, WHITE, NO_COLOR
    }

    enum Type {
        KING('k', 0),
        QUEEN('q', 9),
        ROOK('r', 5),
        BISHOP('b', 3),
        KNIGHT('n', 2.5),
        PAWN('p', 1),
        NO_PIECE('.', 0);

        /**
         * 기물의 표현 방식 <br/>
         * 흑색일 경우 대문자로 치환된다.
         */
        private final char representation;
        private final double defaultScore;

        Type(char representation, double defaultScore) {
            this.representation = representation;
            this.defaultScore = defaultScore;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }

        public double getDefaultScore() {
            return defaultScore;
        }
    }

    Type getPieceType();

    Color getColor();

    char getRepresentation();

    boolean isBlack();

    boolean isWhite();

    boolean isBlank();

    boolean isSameColor(Piece piece1, Piece piece2);
}
