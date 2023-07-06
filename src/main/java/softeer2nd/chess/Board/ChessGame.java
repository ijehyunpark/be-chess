package softeer2nd.chess.Board;

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
     * 기본 룰의 보드판을 초기화한다.
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
     * 보드판의 기물을 교환한다. <br/>
     * 구체적으로 보드판에서 sourcePosition와 targetPosition의 위치를 교환한다.
     */
    public static void move(Board board, Board.Position sourcePosition, Board.Position targetPosition) {
        Piece source = board.findPiece(sourcePosition);
        Piece target = board.findPiece(targetPosition);

        board.assignPiece(sourcePosition, target);
        board.assignPiece(targetPosition, source);
    }
}
