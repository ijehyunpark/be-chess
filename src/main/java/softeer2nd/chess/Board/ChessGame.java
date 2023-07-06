package softeer2nd.chess.Board;

import softeer2nd.chess.pieces.King;
import softeer2nd.chess.pieces.Piece;

import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Type.*;

public class ChessGame {
    @Deprecated
    public static final Piece.Type[][] PIECE_MAP = {
            { ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK },
            { PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN },
            { NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE },
            { NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE },
            { NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE },
            { NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE, NO_PIECE },
            { PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN },
            { ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK }
    };

    @Deprecated
    public static final Piece.Color[][] COLOR_MAP = {
            { BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, },
            { BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, BLACK, },
            { NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR},
            { NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR},
            { NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR},
            { NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR, NO_COLOR},
            { WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, },
            { WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, WHITE, }
    };

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
        board.clear();
        board.initPieces(BASIC_BOARD);
    }

    /**
     * 테스트를 위해 사용되는 메소드이다. <br/>
     * 체스 보드판을 초기화한다. 특정 체스판으로 초기화된다.
     */
    public static void initialize(Board board, String pieceAndColorMap) {
        board.clear();
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
     */
    public static void move(Board board, Board.Position sourcePosition, Board.Position targetPosition) {
        if(targetPosition.getYPos() < 0 || targetPosition.getYPos() >= Board.COLUMN_NUMBER ||
                targetPosition.getXPos() < 0 || targetPosition.getXPos() >= Board.ROW_NUMBER)
            throw new IllegalArgumentException("보드판 범위를 벗어나는 입력입니다.");

        Piece source = board.findPiece(sourcePosition);
        Piece target = board.findPiece(targetPosition);

        if(source.getColor() == target.getColor())
            throw new IllegalArgumentException("같은 편 기물이 존재합니다.");

        switch (source.getPieceType()){
            case KING:
                King.createBlackKing().move(board, sourcePosition, targetPosition);
                break;
            case QUEEN:
                break;
            case ROOK:
                break;
            case BISHOP:
                break;
            case KNIGHT:
                break;
            case PAWN:
                break;
            case NO_PIECE:
                break;
        }
        board.assignPiece(sourcePosition, target);
        board.assignPiece(targetPosition, source);
    }
}
