package softeer2nd.chess.pieces;

import softeer2nd.chess.Board.Board;

import java.util.List;

/**
 * 체스 게임에 사용되는 각 기물들을 정의하고 있다. <br />
 * Note: 만약 해당 클래스로 인스턴스를 생성하면 어떠한 기물 종류에도 포함되지 않고, 색깔을 보유하지 않는다. <br/>
 * 보드판의 빈 칸을 표현하기 위해 사용된다.
 */
public class BlankPiece implements Piece {
    private final Color color;
    private final Type type;
    private static final BlankPiece BLANK_PIECE_INSTANCE = new BlankPiece();

    private BlankPiece() {
        this.color = Color.NO_COLOR;
        this.type = Type.NO_PIECE;
    }
    protected BlankPiece(Color color, Type type){
        this.color = color;
        this.type = type;
    }

    /**
     * 원시적인 기물의 싱글톤 인스턴스를 가져온다.
     */
    public static BlankPiece createBlank() {
        return BlankPiece.BLANK_PIECE_INSTANCE;
    }

    protected void verifyTargetMove(Board.Position target, List<Direction> moveAble){
        boolean isMoveAble = moveAble.stream()
                .anyMatch(direction ->
                        target.getYPos() == direction.getY() &&
                        target.getXPos() == direction.getX());

        if(!isMoveAble)
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 범위입니다.");
    }

    public void verifyMove(Board board, Board.Position source, Board.Position target) {
        throw new IllegalArgumentException("정의되지 않는 기물(빈 기물)은 이동할 수 없습니다.");
    }

    public Color getColor(){
        return this.color;
    }
    public Type getPieceType() {
        return this.type;
    };

    public char getRepresentation() {
        return color == Color.BLACK ?
                type.getBlackRepresentation() : type.getWhiteRepresentation();
    }

    public boolean isBlack() {
        return getColor() == Color.BLACK;
    }

    public boolean isWhite() {
        return getColor() == Color.WHITE;
    }
}
