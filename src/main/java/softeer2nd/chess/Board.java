package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;

import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Type.*;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

/**
 * 체스 보드판을 나타낸다.
 */
public class Board {
    public static final int COLUMN_NUMBER = 8;
    public static final int ROW_NUMBER = 8;
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
    private final ArrayList<ArrayList<Piece>> pieces = new ArrayList<>();

    /**
     * 현재 체스 보드판에 존재하는 체스 말의 개수를 찾는다.
     * @return 체스 보드판 내의 체스 말 개수
     */
    public int pieceCount() {
        return (int) pieces.stream()
                .flatMap(ArrayList::stream)
                .filter(piece -> piece.getPieceType() != NO_PIECE)
                .count();
    }

    /**
     * 초기 폰 객체를 배치한다.
     */
    public void initPieces() {
        for (int col = 0; col < COLUMN_NUMBER; col++) {
            ArrayList<Piece> rowPieces = new ArrayList<>();
            for (int row = 0; row < ROW_NUMBER; row++) {
                rowPieces.add(PIECE_MAP[col][row] == NO_PIECE ?
                                Piece.createBlank() : Piece.createPiece(PIECE_MAP[col][row], COLOR_MAP[col][row]));
            }
            pieces.add(rowPieces);
        }
    }

    /**
     * 체스 보드판을 초기화한다.
     */
    public void initialize() {
        pieces.clear();
        initPieces();
    }

    /**
     * 현재 체스 보드판의 상태를 문자열로 변환한다.
     * @return 현재 체스 보드판의 상태를 나타내는 문자열
     */
    public String showBoard() {
        StringBuilder builder = new StringBuilder();
        for (int col = 0; col < COLUMN_NUMBER; col++) {
            for (int row = 0; row < ROW_NUMBER; row++) {
                Piece piece = pieces.get(col).get(row);
                builder.append(piece.getRepresentation());
            }
            appendNewLine(builder);
        }
        return builder.toString();
    }
}
