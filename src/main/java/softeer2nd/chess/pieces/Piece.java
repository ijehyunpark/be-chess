package softeer2nd.chess.pieces;

import softeer2nd.chess.Board.Board;

public interface Piece {
    enum Color {
        BLACK, WHITE, NO_COLOR;
    }
    enum Type {
        KING('k', 0), QUEEN('q', 9), ROOK('r',5),
        BISHOP('b', 3), KNIGHT('n', 2.5), PAWN('p', 1),
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
    enum BasicDirection {
        NORTH(-1, 0),
        NORTHEAST(1, -1),
        EAST(0, 1),
        SOUTHEAST(1, 1),
        SOUTH(1, 0),
        SOUTHWEST(-1, 1),
        WEST(0, -1),
        NORTHWEST(-1, -1),

        NNE(1, 2),
        NNW(-1, 2),
        SSE(1, -2),
        SSW(-1, -2),
        EEN(2, 1),
        EES(2, -1),
        WWN(-2, 1),
        WWS(-2, -1);


        private final int yDegree;
        private final int xDegree;

        BasicDirection(int yDegree, int xDegree) {
            this.yDegree = yDegree;
            this.xDegree = xDegree;
        }

        public int getYDegree() {
            return yDegree;
        }

        public int getXDegree() {
            return xDegree;
        }
    }
    class Direction {
        private int y;
        private int x;

        public Direction(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }

    void verifyMove(Board board, Board.Position source, Board.Position target);

    Color getColor();
    Type getPieceType();
    char getRepresentation();
    boolean isBlack();
    boolean isWhite();
}
