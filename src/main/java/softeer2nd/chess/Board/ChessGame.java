package softeer2nd.chess.Board;

import softeer2nd.chess.pieces.BlankPiece;
import softeer2nd.chess.pieces.MovablePiece;
import softeer2nd.chess.pieces.Piece;

import javax.xml.transform.Source;

public class ChessGame {
    public static final String BASIC_BOARD =
            "RNBQKBNR" +
            "PPPPPPPP" +
            "........" +
            "........" +
            "........" +
            "........" +
            "pppppppp" +
            "rnbqkbnr";

    /**
     * 기본 룰의 보드판으로 초기화한다.
     */
    public static void initialize(Board board) {
        board.initPieces(BASIC_BOARD);
    }

    /**
     * 테스트를 위해 사용되는 메소드이다. <br/>
     * 체스 보드판을 초기화한다. 특정 체스판으로 초기화된다.
     */
    public static void initialize(Board board, String pieceAndColorMap) {
        board.initPieces(pieceAndColorMap);
    }

    /**
     * 테스트를 위한 메소드이다.
     * 기물이 없는 보드판을 배치한다.
     */
    public static void initializeEmpty(Board board) {
        String emptyMap =
                "........" +
                "........" +
                "........" +
                "........" +
                "........" +
                "........" +
                "........" +
                "........";
        initialize(board, emptyMap);
    }


    /**
     * 보드판에서 한 기물을 이동시킨다. <br/>
     * 구체적으로 가능한 이동일 경우 보드판에서 sourcePosition와 targetPosition의 위치를 교환한다.
     *
     * @throws IllegalArgumentException 범위를 벗어나는 이동, 같은 색깔 기물로 이동, 빈 칸 이동의 경우 발생한다.
     */
    public static void move(Board board, Board.Position sourcePosition, Board.Position destinationPosition) {
        if (destinationPosition.getYPos() < 0 || destinationPosition.getYPos() >= Board.COLUMN_NUMBER ||
                destinationPosition.getXPos() < 0 || destinationPosition.getXPos() >= Board.ROW_NUMBER) {
            throw new IllegalArgumentException("보드판 범위를 벗어나는 입력입니다.");
        }

        Piece sourcePiece = board.findPiece(sourcePosition);
        Piece destinationPiece = board.findPiece(destinationPosition);

        if (sourcePiece.isBlank()) {
            throw new IllegalArgumentException("빈 칸입니다.");
        }

        MovablePiece sourceMovalbePiece = (MovablePiece) sourcePiece;

        sourceMovalbePiece.verifyMove(board, sourcePosition, destinationPosition);

        // 다른 색 기물일 경우 제거한다.
        if ((sourceMovalbePiece.getColor() != destinationPiece.getColor()) && sourceMovalbePiece.getColor() != Piece.Color.NO_COLOR && destinationPiece.getColor() != Piece.Color.NO_COLOR) {
            destinationPiece = BlankPiece.createBlank();
        }

        board.assignPiece(sourcePosition, destinationPiece);
        board.assignPiece(destinationPosition, sourceMovalbePiece);
    }
}
